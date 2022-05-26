package com.itmuch.usercenter.service.impl;


import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmuch.usercenter.dto.user.UserLoginDTO;
import com.itmuch.usercenter.model.User;
import com.itmuch.usercenter.mapper.UserMapper;
import com.itmuch.usercenter.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * user - 分享
 *
 * @author liangbingtian
 */
@Service(value = "userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

  @Override
  public User getById(Integer id) {
    return super.getById(id);
  }

  @Override
  public User login(UserLoginDTO loginDTO, String openId) {
    User one = getOne(Wrappers.lambdaQuery(User.class).eq(User::getWxId, openId));
    if (one == null) {
      User userToSave = User.builder().wxId(openId).bonus(300).wxNickname(loginDTO.getWxNickName())
          .avatarUrl(loginDTO.getAvatarUrl()).roles("user").build();
      save(userToSave);
      return userToSave;
    }
    return one;
  }


}
