package com.itmuch.contentcenter.rocketmq;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * @author liangbingtian
 * @date 2022/05/12 下午4:07
 */
@RocketMQTransactionListener
public class AddBonusTransactionListener implements RocketMQLocalTransactionListener {

  @Override
  public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
    return null;
  }

  @Override
  public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
    return null;
  }
}
