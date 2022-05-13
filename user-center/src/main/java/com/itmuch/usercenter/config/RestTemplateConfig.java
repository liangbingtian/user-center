package com.itmuch.usercenter.config;

import com.itmuch.usercenter.util.ApiCaller;
import java.time.Duration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate的相关配置
 *
 * @author liangbingtian
 * @date 2022/05/12 下午7:05
 */
@Configuration
public class RestTemplateConfig {

  public static final int READ_TIMEOUT_60 = 60;
  public static final int CONN_TIMEOUT_5 = 5;

  @Bean
  @Primary
  public RestTemplate restTemplate(RestTemplateBuilder builder) {
    return builder.setReadTimeout(Duration.ofSeconds(READ_TIMEOUT_60)).
        setConnectTimeout(Duration.ofSeconds(CONN_TIMEOUT_5)).build();
  }

  @Bean
  @Primary
  public ApiCaller apiCaller(RestTemplate restTemplate) {
    ApiCaller apiCaller = new ApiCaller();
    apiCaller.setRestTemplate(restTemplate);
    return apiCaller;
  }
}
