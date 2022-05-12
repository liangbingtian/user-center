package com.itmuch.usercenter.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  @TableId(type = IdType.AUTO)
  private Integer id;

  private Integer userId;

  private Integer value;

  private String event;

  private Date createTime;

  private String description;

}
