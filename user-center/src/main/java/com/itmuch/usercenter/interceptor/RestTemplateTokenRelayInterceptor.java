package com.itmuch.usercenter.interceptor;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 拦截器
 *
 * @author liangbingtian
 * @date 2022/05/24 下午4:24
 */
public class RestTemplateTokenRelayInterceptor implements ClientHttpRequestInterceptor {

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body,
      ClientHttpRequestExecution execution) throws IOException {
    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
        .getRequestAttributes();
    HttpServletRequest httpRequest = requestAttributes.getRequest();
    String token = httpRequest.getHeader("X-Token");
    HttpHeaders headers = request.getHeaders();
    headers.add("X-Token", token);
    //责任链模式
    return execution.execute(request, body);
  }
}
