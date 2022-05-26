package com.itmuch.usercenter.auth;

import com.itmuch.usercenter.security.SecurityException;
import com.itmuch.usercenter.util.JwtOperator;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 检查是否登录的切面
 *
 * @author liangbingtian
 * @date 2022/05/20 下午1:30
 */
@Aspect
@Component
public class CheckLoginAspect {

  @Autowired
  private JwtOperator jwtOperator;

  @Around("@annotation(com.itmuch.usercenter.auth.CheckLogin)")
  public Object checkLogin(ProceedingJoinPoint point) {
    try {
      //1. 从header里获取token
      RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
      ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
      HttpServletRequest request = attributes.getRequest();

      String token = request.getHeader("X-Token");
      //2. 校验token是否合法并且是否过期，如果不合法直接抛异常；如果合法，直接放行
      Boolean isValid = jwtOperator.validateToken(token);
      if (!isValid) {
        throw new SecurityException("Token不合法！");
      }
      //3. 如果校验成功，那么将用户信息设置到request的attribute里面,claims是设置的用户信息
      Claims claims = jwtOperator.getClaimsFromToken(token);
      request.setAttribute("id", claims.get("id"));
      return point.proceed();
    } catch (Throwable throwable) {
      throw new SecurityException("Token不合法！");
    }
  }
}
