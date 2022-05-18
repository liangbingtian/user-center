package com.itmuch.usercenter.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itmuch.usercenter.model.User;
import com.itmuch.usercenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangbingtian
 * @date 2022/05/04 下午3:38
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

  @Autowired
  private IService<User> userService;

  @GetMapping(value = "/{id}")
  public User findById(@PathVariable Integer id) {
    return userService.getById(id.longValue());
  }



}
