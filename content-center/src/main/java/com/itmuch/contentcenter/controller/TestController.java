package com.itmuch.contentcenter.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangbingtian
 * @date 2022/05/05 下午4:59
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

  @Autowired
  private DiscoveryClient discoveryClient;

  @GetMapping(value = "/test2")
  public List<ServiceInstance> setDiscoveryClient() {
    return discoveryClient.getInstances("user-center");
  }

}
