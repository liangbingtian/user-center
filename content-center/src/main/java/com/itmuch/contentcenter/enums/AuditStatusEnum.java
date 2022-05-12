package com.itmuch.contentcenter.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审计状态的枚举类
 *
 * @author liangbingtian
 * @date 2022/05/09 下午12:12
 */
@Getter
@AllArgsConstructor
public enum  AuditStatusEnum {
  /**
   * 待审核
   */
  NOT_YET,
  /**
   * 审核通过
   */
  PASS,
  /**
   * 拒绝
   */
  REJECT;

}
