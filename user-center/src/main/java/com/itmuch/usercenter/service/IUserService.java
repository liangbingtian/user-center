package com.itmuch.usercenter.service;


import com.itmuch.usercenter.dto.user.UserLoginDTO;
import com.itmuch.usercenter.model.User;

/**
 * user - 分享
 *
 * @author liangbingtian
 */
public interface IUserService {

  User getById(Integer id);

  User login(UserLoginDTO loginDTO, String openId);

}
