package com.itmuch.contentcenter.model;

import io.mybatis.provider.Entity;
import org.apache.ibatis.type.JdbcType;


/**
 * rocketmq_transaction_log - RocketMQ事务日志表
 *
 * @author liangbingtian
 */
@Entity.Table(value = "rocketmq_transaction_log", remark = "RocketMQ事务日志表", autoResultMap = true)
public class RocketmqTransactionLog {
  @Entity.Column(value = "id", id = true, remark = "id", updatable = false, insertable = false)
  private Integer id;

  @Entity.Column(value = "transaction_Id", remark = "事务id")
  private String transactionId;

  @Entity.Column(value = "log", remark = "日志")
  private String log;


  /**
   * 获取 id
   *
   * @return id - id
   */
  public Integer getId() {
    return id;
  }

  /**
   * 设置id
   *
   * @param id id
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * 获取 事务id
   *
   * @return transactionId - 事务id
   */
  public String getTransactionId() {
    return transactionId;
  }

  /**
   * 设置事务id
   *
   * @param transactionId 事务id
   */
  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  /**
   * 获取 日志
   *
   * @return log - 日志
   */
  public String getLog() {
    return log;
  }

  /**
   * 设置日志
   *
   * @param log 日志
   */
  public void setLog(String log) {
    this.log = log;
  }

}
