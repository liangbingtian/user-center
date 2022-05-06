package com.itmuch.usercenter.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmuch.usercenter.model.User;
import com.itmuch.usercenter.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * user - 分享
 *
 * @author liangbingtian
 */
@Service(value = "userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  {

}
