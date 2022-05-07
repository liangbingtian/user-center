package com.itmuch.usercenter.api;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itmuch.usercenter.dto.UserDTO;
import com.itmuch.usercenter.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 对外提供feign服务的api
 *
 * @author liangbingtian
 * @date 2022/05/07 下午8:46
 */
@RestController
public class UserApiController implements IUserApiService{

  @Autowired
  private IService<User> userService;

  public UserDTO findById(Integer id) {
    User user = userService.getById(id.longValue());
    UserDTO result = new UserDTO();
    BeanUtils.copyProperties(user, result);
    return result;
  }
}
