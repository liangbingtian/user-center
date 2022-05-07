package com.itmuch.contentcenter.configuration;

import feign.Logger;
import feign.Logger.Level;
import org.springframework.context.annotation.Bean;

/**
 * 用户中心feign的配置
 *
 * @author liangbingtian
 * @date 2022/05/07 下午3:32
 */
public class UserCenterFeignConfiguration {

  @Bean
  public Logger.Level level() {
    // 让feign打印所有的请求细节
    return Level.FULL;
  }

}
