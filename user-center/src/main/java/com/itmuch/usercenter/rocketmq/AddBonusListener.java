package com.itmuch.usercenter.rocketmq;

import com.itmuch.usercenter.dto.message.UserAddBonusMsgDTO;
import com.itmuch.usercenter.mapper.BonusEventLogMapper;
import com.itmuch.usercenter.mapper.UserMapper;
import com.itmuch.usercenter.model.BonusEventLog;
import com.itmuch.usercenter.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * mq添加积分的监听类
 *
 * @author liangbingtian
 * @date 2022/05/12 上午9:46
 */
@Service
@RocketMQMessageListener(consumerGroup = "consumer-group", topic = "add-bonus")
@Slf4j
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private BonusEventLogMapper bonusEventLogMapper;

  @Override
  @Transactional
  public void onMessage(UserAddBonusMsgDTO message) {
    //1.为用户加积分
    Integer userId = message.getUserId();
    User user = userMapper.selectById(userId);
    Integer bonus = message.getBonus();
    user.setBonus(user.getBonus() + bonus);
    userMapper.updateById(user);
    //2.记录日志到bonus_event_log表里
    bonusEventLogMapper
        .insert(BonusEventLog.builder().userId(userId).value(bonus).event("CONTRIBUTE")
            .description("投稿加积分").build());
    log.info("积分添加完毕...");
  }
}
