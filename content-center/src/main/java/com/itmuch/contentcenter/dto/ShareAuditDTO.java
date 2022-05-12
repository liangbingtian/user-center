package com.itmuch.contentcenter.dto;

import com.itmuch.contentcenter.enums.AuditStatusEnum;
import lombok.Data;

/**
 * @author liangbingtian
 * @date 2022/05/09 下午12:10
 */
@Data
public class ShareAuditDTO {

  /**
   * 审核状态
   */
  private AuditStatusEnum auditStatusEnum;

  /**
   * 原因
   */
  private String reason;

}
