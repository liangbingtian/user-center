package com.itmuch.contentcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liangbingtian
 * @date 2022/05/04 下午5:00
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShareDTO {
  private Integer id;

  private String wxId;

  private String wxNickname;

  private String roles;

  private String avatarUrl;

  private Integer bonus;

}
