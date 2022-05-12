package com.itmuch.usercenter.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liangbingtian
 * @date 2022/05/11 上午9:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddBonusMsgDTO {
  //为谁加积分
  private Integer userId;
  //加多少积分
  private Integer bonus;
}
