package com.itmuch.usercenter;

import com.itmuch.usercenter.mapper.UserMapper;
import com.itmuch.usercenter.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserCenterApplication.class)
@Slf4j
public class UserCenterApplicationTests {

  @Autowired
  private UserMapper userMapper;

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

}
