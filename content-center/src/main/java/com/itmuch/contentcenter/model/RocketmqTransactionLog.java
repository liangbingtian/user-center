package com.itmuch.contentcenter.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * rocketmq_transaction_log - RocketMQ事务日志表
 *
 * @author liangbingtian
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RocketmqTransactionLog {

  @TableId(value = "id", type = IdType.AUTO)
  private Integer id;

  private String transactionId;

  private String log;

}
