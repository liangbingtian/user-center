package com.itmuch.contentcenter.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import org.apache.ibatis.annotations.Insert;

/**
 * share - 分享表
 *
 * @author liangbingtian
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Share {
  @TableId
  private Integer id;

  private Integer userId;

  private String title;

  @TableField(exist = false)
  private Date createTime;

  @TableField(exist = false)
  private Date updateTime;

  private Boolean isOriginal;

  private String author;

  private String cover;

  private String summary;

  private Integer price;

  private String downloadUrl;

  private Integer buyCount;

  private Boolean showFlag;

  private String auditStatus;

  private String reason;

}
