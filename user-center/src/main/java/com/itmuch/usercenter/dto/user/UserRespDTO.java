package com.itmuch.usercenter.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录过程中的用户响应类
 * @author liangbingtian
 * @date 2022/05/17 上午11:38
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserRespDTO {

  private String id;
  private String avatarUrl;
  private String bonus;
  private String wxNickName;
}
