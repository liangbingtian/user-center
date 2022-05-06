package com.itmuch.contentcenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itmuch.contentcenter.model.RocketmqTransactionLog;
import org.springframework.stereotype.Repository;

/**
 * rocketmq_transaction_log - RocketMQ事务日志表
 *
 * @author liangbingtian
 */
@Repository
public interface RocketmqTransactionLogMapper extends BaseMapper<RocketmqTransactionLog> {

}