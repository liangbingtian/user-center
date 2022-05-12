package com.itmuch.usercenter.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 调用任意url的示例
 *
 * @author liangbingtian
 * @date 2022/05/08 下午6:59
 */
//脱离ribbon的使用方式
@FeignClient(value = "baidu", url = "http://www.baidu.com")
public interface AnyUrlClient {

  @GetMapping()
  String index();
}
