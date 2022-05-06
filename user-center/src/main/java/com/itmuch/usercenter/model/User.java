package com.itmuch.usercenter.model;

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
  private Integer id;

  private String wxId;

  private String wxNickname;

  private String roles;

  private String avatarUrl;

  private Date createTime;

  private Date updateTime;

  private Integer bonus;

}
