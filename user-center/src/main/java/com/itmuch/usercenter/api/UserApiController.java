package com.itmuch.usercenter.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itmuch.usercenter.dto.UserDTO;
import com.itmuch.usercenter.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对外提供feign服务的api
 *
 * @author liangbingtian
 * @date 2022/05/07 下午8:46
 */
@RestController
public class UserApiController implements IUserApiService {

  @Autowired
  private IService<User> userService;

  public UserDTO findById(Integer id) {
    User user = userService.getById(id.longValue());
    UserDTO result = new UserDTO();
    BeanUtils.copyProperties(user, result);
    return result;
  }

  @Override
  public UserDTO query(UserDTO userDTO) {
    LambdaQueryWrapper<User> userQueryWrapper = Wrappers.lambdaQuery();
    userQueryWrapper.eq(User::getId, userDTO.getId())
        .eq(!StringUtils.isEmpty(userDTO.getWxId()), User::getWxId, userDTO.getWxId())
        .eq(!StringUtils.isEmpty(userDTO.getWxNickname()), User::getWxNickname,
            userDTO.getWxNickname())
        .eq(!StringUtils.isEmpty(userDTO.getAvatarUrl()), User::getAvatarUrl,
            userDTO.getAvatarUrl())
        .eq(!StringUtils.isEmpty(userDTO.getBonus()), User::getBonus, userDTO.getBonus());
    User one = userService.getOne(userQueryWrapper);
    UserDTO result = new UserDTO();
    BeanUtils.copyProperties(one, result);
    return result;
  }
}
