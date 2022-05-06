package com.itmuch.contentcenter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * mid_user_share - 用户-分享中间表【描述用户购买的分享】
 *
 * @author liangbingtian
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MidUserShare {

  private Integer id;

  private Integer shareId;

  private Integer userId;

}
