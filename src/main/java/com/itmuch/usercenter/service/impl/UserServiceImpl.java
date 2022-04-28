package com.itmuch.usercenter.service.impl;

import io.mybatis.service.AbstractService;

import com.itmuch.usercenter.service.UserService;
import com.itmuch.usercenter.mapper.UserMapper;
import com.itmuch.usercenter.model.User;
import org.springframework.stereotype.Service;

/**
 * user - 分享
 *
 * @author liangbingtian
 */
@Service
public class  UserServiceImpl extends AbstractService<User, Long, UserMapper> implements UserService {

}
