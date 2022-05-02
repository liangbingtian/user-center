package com.itmuch.contentcenter.service.impl;

import io.mybatis.service.AbstractService;

import com.itmuch.contentcenter.service.RocketmqTransactionLogService;
import com.itmuch.contentcenter.mapper.RocketmqTransactionLogMapper;
import com.itmuch.contentcenter.model.RocketmqTransactionLog;
import org.springframework.stereotype.Service;

/**
 * rocketmq_transaction_log - RocketMQ事务日志表
 *
 * @author liangbingtian
 */
@Service
public class  RocketmqTransactionLogServiceImpl extends AbstractService<RocketmqTransactionLog, Long, RocketmqTransactionLogMapper> implements RocketmqTransactionLogService {

}
