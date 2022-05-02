package com.itmuch.usercenter;

import com.itmuch.usercenter.mapper.UserMapper;
import com.itmuch.usercenter.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserCenterApplication.class)
public class UserCenterApplicationTests {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void contextLoads() {
    User user = new User();
    user.setAvatarUrl("XXX");
    user.setBonus(200);
    userMapper.insertSelective(user);
    Assert.assertNotNull(user.getId());
  }

}
