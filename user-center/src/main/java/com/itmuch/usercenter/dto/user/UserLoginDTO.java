package com.itmuch.usercenter.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liangbingtian
 * @date 2022/05/17 上午11:43
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserLoginDTO {
  private String code;
  private String avatarUrl;
  private String wxNickName;

}
