package com.itmuch.usercenter.api;

import com.itmuch.usercenter.dto.UserDTO;
import org.springframework.cloud.openfeign.SpringQueryMap;
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

  /**
   * 但是这种继承的写法不支持@SpringQueryMap。支持这种继承的话可以使用@RequestParam
   */
  @GetMapping(value = "/privateapi/query")
  UserDTO query(@SpringQueryMap UserDTO userDTO);

}
