package com.itmuch.contentcenter.dto;

import lombok.Data;

/**
 * @author liangbingtian
 * @date 2022/05/04 下午4:51
 */
@Data
public class UserDTO {

  private Integer id;

  private String wxId;

  private String wxNickname;

  private String roles;

  private String avatarUrl;

  private Integer bonus;
}
