package com.itmuch.usercenter.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itmuch.usercenter.auth.CheckLogin;
import com.itmuch.usercenter.dto.user.JwtTokenRespDTO;
import com.itmuch.usercenter.dto.user.LoginRespDTO;
import com.itmuch.usercenter.dto.user.UserLoginDTO;
import com.itmuch.usercenter.dto.user.UserRespDTO;
import com.itmuch.usercenter.model.User;
import com.itmuch.usercenter.service.IUserService;
import com.itmuch.usercenter.util.JwtOperator;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class UserController {

  @Autowired
  private IUserService userService;

  @Autowired
  private WxMaService wxMaService;

  @Autowired
  private JwtOperator jwtOperator;

  @GetMapping(value = "/{id}")
  @CheckLogin
  public User findById(@PathVariable Integer id) {
    return userService.getById(id);
  }

  @GetMapping(value = "/gen-token")
  public String genToken() {
    //颁发token
    Map<String, Object> userInfo = new HashMap<>(3);
    userInfo.put("id", 1);
    userInfo.put("wxNickname", "xxxx");
    userInfo.put("role", "user");
    return jwtOperator.generateToken(userInfo);
  }

  @PostMapping(value = "/login")
  public LoginRespDTO login(UserLoginDTO loginDTO) throws WxErrorException {
    WxMaJscode2SessionResult result = wxMaService.getUserService()
        .getSessionInfo(loginDTO.getCode());
    //用户在微信这边的唯一标识
    String openid = result.getOpenid();
    //如果用户未注册，那么就插入
    User user = userService.login(loginDTO, openid);
    //颁发token
    Map<String, Object> userInfo = new HashMap<>(3);
    userInfo.put("id", user.getId());
    userInfo.put("wxNickname", user.getWxNickname());
    userInfo.put("role", user.getRoles());
    String token = jwtOperator.generateToken(userInfo);
    log.info("用户{}登录成功，生成的token={}，有效期到:{}", loginDTO.getWxNickName()
        , token
        , jwtOperator.getExpirationDateFromToken(token));
    return LoginRespDTO
        .builder().user(UserRespDTO.builder()
            .id(user.getId())
            .avatarUrl(user.getAvatarUrl())
            .bonus(user.getBonus())
            .wxNickName(user.getWxNickname())
            .build())
        .token(JwtTokenRespDTO.builder()
            .expirationTime(jwtOperator.getExpirationDateFromToken(token).getTime())
            .token(token)
            .build())
        .build();
  }


}
