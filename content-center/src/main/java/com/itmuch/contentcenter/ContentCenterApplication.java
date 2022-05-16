package com.itmuch.contentcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.itmuch.contentcenter.mapper")
//使用defaultConfiguration可以进行全局配置feign
@EnableFeignClients(basePackages = "com.itmuch")
public class ContentCenterApplication {

  public static void main(String[] args) {
    SpringApplication.run(ContentCenterApplication.class, args);
  }

}
