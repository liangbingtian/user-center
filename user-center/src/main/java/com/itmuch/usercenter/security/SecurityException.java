package com.itmuch.usercenter.security;

/**
 * @author liangbingtian
 * @date 2022/05/20 下午3:14
 */
public class SecurityException extends RuntimeException{

  public SecurityException() {
  }

  public SecurityException(String message) {
    super(message);
  }
}
