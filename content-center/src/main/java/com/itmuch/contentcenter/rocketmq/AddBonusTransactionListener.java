package com.itmuch.contentcenter.rocketmq;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itmuch.contentcenter.dto.ShareAuditDTO;
import com.itmuch.contentcenter.mapper.RocketmqTransactionLogMapper;
import com.itmuch.contentcenter.model.RocketmqTransactionLog;
import com.itmuch.contentcenter.service.ShareService;
import java.util.Objects;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * @author liangbingtian
 * @date 2022/05/12 下午4:07
 */
@RocketMQTransactionListener
public class AddBonusTransactionListener implements RocketMQLocalTransactionListener {

  @Autowired
  private ShareService shareService;

  @Autowired
  private RocketmqTransactionLogMapper transactionLogMapper;

  @Override
  public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
    MessageHeaders headers = message.getHeaders();
    String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
    Integer shareId = Integer.valueOf((String) Objects.requireNonNull(headers.get("share_id")));
    //执行本地事务
    try {
      shareService.auditByIdWithRocketMqLog(shareId, (ShareAuditDTO) o, transactionId);
      return RocketMQLocalTransactionState.COMMIT;
    } catch (Exception e) {
      return RocketMQLocalTransactionState.ROLLBACK;
    }
  }

  /**
   * RocketMQ回查本地事务状态。
   *
   * @param message
   * @return
   */
  @Override
  public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
    MessageHeaders headers = message.getHeaders();
    String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
    RocketmqTransactionLog log = transactionLogMapper.selectOne(
        new LambdaQueryWrapper<RocketmqTransactionLog>()
            .eq(RocketmqTransactionLog::getTransactionId, transactionId));
    if (log!=null) {
      return RocketMQLocalTransactionState.COMMIT;
    }
    return RocketMQLocalTransactionState.ROLLBACK;
  }
}
