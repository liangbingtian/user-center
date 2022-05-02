package com.itmuch.usercenter.model;

import io.mybatis.provider.Entity;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * bonus_event_log - 积分变更记录表
 *
 * @author liangbingtian
 */
@Entity.Table(value = "bonus_event_log", remark = "积分变更记录表", autoResultMap = true)
public class BonusEventLog {
  @Entity.Column(value = "id", id = true, remark = "Id", updatable = false, insertable = false)
  private Integer id;

  @Entity.Column(value = "user_id", remark = "user.id")
  private Integer userId;

  @Entity.Column(value = "value", remark = "积分操作值")
  private Integer value;

  @Entity.Column(value = "event", remark = "发生的事件")
  private String event;

  @Entity.Column(value = "create_time", remark = "创建时间", jdbcType = JdbcType.TIMESTAMP)
  private Date createTime;

  @Entity.Column(value = "description", remark = "描述")
  private String description;


  /**
   * 获取 Id
   *
   * @return id - Id
   */
  public Integer getId() {
    return id;
  }

  /**
   * 设置Id
   *
   * @param id Id
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * 获取 user.id
   *
   * @return userId - user.id
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * 设置user.id
   *
   * @param userId user.id
   */
  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  /**
   * 获取 积分操作值
   *
   * @return value - 积分操作值
   */
  public Integer getValue() {
    return value;
  }

  /**
   * 设置积分操作值
   *
   * @param value 积分操作值
   */
  public void setValue(Integer value) {
    this.value = value;
  }

  /**
   * 获取 发生的事件
   *
   * @return event - 发生的事件
   */
  public String getEvent() {
    return event;
  }

  /**
   * 设置发生的事件
   *
   * @param event 发生的事件
   */
  public void setEvent(String event) {
    this.event = event;
  }

  /**
   * 获取 创建时间
   *
   * @return createTime - 创建时间
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * 设置创建时间
   *
   * @param createTime 创建时间
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * 获取 描述
   *
   * @return description - 描述
   */
  public String getDescription() {
    return description;
  }

  /**
   * 设置描述
   *
   * @param description 描述
   */
  public void setDescription(String description) {
    this.description = description;
  }

}
