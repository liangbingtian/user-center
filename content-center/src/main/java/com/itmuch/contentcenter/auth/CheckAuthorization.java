package com.itmuch.contentcenter.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 检查注解
 *
 * @author liangbingtian
 * @date 2022/05/24 下午4:37
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthorization {
  String value();

}
