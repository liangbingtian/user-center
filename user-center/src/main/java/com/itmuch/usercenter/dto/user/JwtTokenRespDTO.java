package com.itmuch.usercenter.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * jwttoken的返回值
 *
 * @author liangbingtian
 * @date 2022/05/17 上午11:34
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class JwtTokenRespDTO {

  private String token;

  private Long expirationTime;

}
