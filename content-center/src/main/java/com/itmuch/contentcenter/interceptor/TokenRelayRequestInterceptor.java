package com.itmuch.contentcenter.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author liangbingtian
 * @date 2022/05/20 下午5:10
 */
public class TokenRelayRequestInterceptor implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate requestTemplate) {
    //1. 获取到token
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest request = requestAttributes.getRequest();
    String token = request.getHeader("X-Token");
    //2. 将token传递
    if (StringUtils.isNotBlank(token)) {
      requestTemplate.header("X-Token", token);
    }
  }
}
