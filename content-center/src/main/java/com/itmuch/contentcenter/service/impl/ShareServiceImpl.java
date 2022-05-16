package com.itmuch.contentcenter.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmuch.contentcenter.dto.ShareAuditDTO;
import com.itmuch.contentcenter.dto.ShareDTO;

import com.itmuch.contentcenter.enums.AuditStatusEnum;
import com.itmuch.contentcenter.mapper.RocketmqTransactionLogMapper;
import com.itmuch.contentcenter.model.RocketmqTransactionLog;
import com.itmuch.contentcenter.service.ShareService;
import com.itmuch.contentcenter.mapper.ShareMapper;
import com.itmuch.contentcenter.model.Share;
import com.itmuch.usercenter.client.AnyUrlClient;
import com.itmuch.usercenter.client.UserClient;
import com.itmuch.usercenter.dto.message.UserAddBonusMsgDTO;
import com.itmuch.usercenter.dto.UserDTO;
import java.util.Objects;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * share - 分享表
 *
 * @author liangbingtian
 */
@Service
@Slf4j
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements ShareService {

  @Autowired
  private ShareMapper shareMapper;

  @Autowired
  private UserClient userClient;

  @Autowired
  private AnyUrlClient anyUrlClient;

  @Autowired
  private RocketMQTemplate rocketMQTemplate;

  @Autowired
  private RocketmqTransactionLogMapper transactionLogMapper;

  @Override
  public ShareDTO findShareById(Integer id) {
    Share share = shareMapper.selectById(id);
    Integer userId = share.getUserId();
    //ribbon把user-center转换为nacos上地址，进行负载均衡算法
    UserDTO userDTO = userClient.findById(userId);
    ShareDTO shareDTO = new ShareDTO();
    BeanUtils.copyProperties(share, shareDTO);
    shareDTO.setWxNickname(userDTO.getWxNickname());
    return shareDTO;
  }

  @Override
  public ShareDTO findByShareCondition(ShareDTO shareDTO) {
    Share share = shareMapper.selectById(shareDTO.getId());
    return null;
  }

  @Override
  public String getAnyIndex() {
    return anyUrlClient.index();
  }

  @Override
  public Share auditById(Integer id, ShareAuditDTO auditDTO) {
    //1.查询share是否存在，不存在或者当前的audit_status!=NOT_YET
    Share share = super.getById(id);
    if (share == null) {
      throw new IllegalArgumentException("参数非法！该分享不存在");
    }
    if (!Objects.equals("NOT_YET", share.getAuditStatus())) {
      throw new IllegalArgumentException("参数非法！该分享已经通过或已经不通过");
    }
    if (AuditStatusEnum.PASS.equals(auditDTO.getAuditStatusEnum())) {
      //发送半消息
      String transactionId = UUID.randomUUID().toString();
      Integer shareId = share.getId();
      rocketMQTemplate
          .sendMessageInTransaction("add-bonus"
              , MessageBuilder.withPayload(UserAddBonusMsgDTO.builder()
                  .userId(share.getUserId()).bonus(50).build())
                  .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                  .setHeader("share_id", shareId)
                  .build()
              , auditDTO);
    }else {
      this.auditByIdInDB(id, auditDTO);
    }
    return share;
  }

  @Transactional(rollbackFor = Exception.class)
  public void auditByIdInDB(Integer id, ShareAuditDTO auditDTO) {
    //2.审核资源，设置状态PASS/REJECT
    Share share = Share.builder().id(id).auditStatus(auditDTO.getAuditStatusEnum().toString())
        .reason(auditDTO.getReason()).build();
    this.updateById(share);
    //todo 把share写到缓存
  }

  @Transactional(rollbackFor = Exception.class)
  public void auditByIdWithRocketMqLog(Integer id, ShareAuditDTO auditDTO, String transactionId) {
    this.auditByIdInDB(id, auditDTO);
    transactionLogMapper
        .insert(RocketmqTransactionLog.builder().transactionId(transactionId).log("审核分享").build());
  }


}
