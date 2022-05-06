package com.itmuch.usercenter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * bonus_event_log - 积分变更记录表
 *
 * @author liangbingtian
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BonusEventLog {
  private Integer id;

  private Integer userId;

  private Integer value;

  private String event;

  private Date createTime;

  private String description;

}
