package com.itmuch.usercenter.util;

import com.alibaba.fastjson.JSON;
import java.util.Collections;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * api调用的请求类
 *
 * @author liangbingtian
 * @date 2022/05/12 下午7:12
 */
@Slf4j
@Setter
public class ApiCaller {

  private RestTemplate restTemplate;

  public <T> T getJsonWithType(String url, Class<T> clazz, Object... urlVariables) {
    return exchange(url, HttpMethod.GET, null, clazz, urlVariables);
  }

  public <T> T postJsonWithType(String url, Object body, Class<T> clazz, Object... urlVariables) {
    return exchange(url, HttpMethod.POST, body, clazz, urlVariables);
  }


  private <T> T exchange(String url, HttpMethod method, Object body, Class<T> respClazz, Object... variables) {
    log.info("开始请求的url:{}, method:{}, url中的参数为:{}", url, method, variables);
    HttpEntity<Object> entity = getHttpEntity(body);
    ResponseEntity<String> respEntity = restTemplate.exchange(url, method, entity, String.class, variables);
    if (!HttpStatus.OK.equals(respEntity.getStatusCode())) {
      throw new RuntimeException("http请求发生异常，状态码为"+respEntity.getStatusCode());
    }
    String result = respEntity.getBody();
    log.info("该请求返回的结果为:{}", result);
    if (StringUtils.isEmpty(result)) {
      throw new RuntimeException("该请求返回的响应体为空");
    }
    return JSON.parseObject(result, respClazz);
  }

  private HttpEntity<Object> getHttpEntity(Object body) {
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    return new HttpEntity<>(body, headers);
  }
}
