package com.itmuch.usercenter.api;

import com.itmuch.usercenter.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * user-center的feign对外提供的接口
 *
 * @author liangbingtian
 * @date 2022/05/07 下午8:43
 */
public interface IUserApiService {

  @GetMapping(value = "/privateapi/users/{id}")
  UserDTO findById(@PathVariable Integer id);

}
