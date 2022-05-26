package com.itmuch.usercenter.client;

import com.itmuch.usercenter.api.IUserApiService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 调用user服务的feignclient
 *
 * @author liangbingtian
 * @date 2022/05/07 下午8:57
 */
//@FeignClient(name = "user-center", configuration = UserCenterFeignConfiguration.class)
@FeignClient(value = "user-center")
public interface UserClient extends IUserApiService {

}
