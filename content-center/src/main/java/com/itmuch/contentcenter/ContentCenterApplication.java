package com.itmuch.contentcenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.itmuch.contentcenter")
public class ContentCenterApplication {

  public static void main(String[] args) {
    SpringApplication.run(ContentCenterApplication.class, args);
  }

}
