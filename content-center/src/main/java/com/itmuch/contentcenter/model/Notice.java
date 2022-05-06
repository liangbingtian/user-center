package com.itmuch.contentcenter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

/**
 * notice - 
 *
 * @author liangbingtian
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
  private Integer id;

  private String content;

  private Boolean showFlag;

  private Date createTime;

}
