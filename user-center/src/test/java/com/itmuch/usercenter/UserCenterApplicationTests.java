package com.itmuch.usercenter;

import com.alibaba.fastjson.JSON;
import com.itmuch.usercenter.dto.WXTokenResponse;
import com.itmuch.usercenter.mapper.UserMapper;
import com.itmuch.usercenter.model.User;
import com.itmuch.usercenter.service.impl.WenhaiWXServiceImpl;
import com.itmuch.usercenter.util.ApiCaller;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserCenterApplication.class)
@Slf4j
public class UserCenterApplicationTests {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private WenhaiWXServiceImpl service;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private ApiCaller apiCaller;

  @Test
  public void contextLoads() {
    User user = new User();
    user.setAvatarUrl("XXX");
    user.setBonus(200);
    userMapper.insert(user);
    Assert.assertNotNull(user.getId());
    log.info("==>  Preparing: SELECT id,user_id,title,create_time,update_time,is_original,author,cover,summary,price,download_url,buy_count,show_flag,audit_status,reason FROM share WHERE id=?\n"
        + "==> Parameters: 1(Integer)");
  }

  @Test
  public void test2() {
    log.info("==>  Preparing: SELECT id,user_id,title,create_time,update_time,is_original,author,cover,summary,price,download_url,buy_count,show_flag,audit_status,reason FROM share WHERE id=?\n"
        + "==> Parameters: 1(Integer)");
  }

  @Test
  public void test3() {
//    String str = restTemplate.getForObject(
//        "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxdc803b08e48ca640&secret=c1386ecd93d46163419264ae8d8428c9",
//        String.class);
//    WXTokenResponse response = JSON.parseObject(str, WXTokenResponse.class);
    String accessToken = service.showQrCode("测试2");
    System.out.println(accessToken);
  }

  @Test
  public void test4() throws UnsupportedEncodingException {
    System.out.println(URLEncoder.encode("{\"a\":\"123\"}", "utf-8"));
  }

  @Test
  public void test5() {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=c8e46ce1-0a8a-444d-b9c0-0a95bd7c2545";
  }

}
