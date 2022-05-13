package com.itmuch.usercenter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 微信请求的参数
 *
 * @author liangbingtian
 * @date 2022/05/12 下午7:40
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "wx")
public class WXRequestConfig {

  private String appId;

  private String appSecret;

  private String requestTokenUrl;

  private String requestTicketUrl;

  private String showQrCode;

}
