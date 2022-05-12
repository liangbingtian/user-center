package com.itmuch.usercenter.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

/**
 * user - 分享
 *
 * @author liangbingtian
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
  @TableId
  private Integer id;

  private String wxId;

  private String wxNickname;

  private String roles;

  private String avatarUrl;

  @TableField(exist = false)
  private Date createTime;

  @TableField(exist = false)
  private Date updateTime;

  private Integer bonus;

}
