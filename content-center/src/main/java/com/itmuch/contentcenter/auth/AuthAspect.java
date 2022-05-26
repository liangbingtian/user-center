package com.itmuch.contentcenter.auth;

import com.itmuch.contentcenter.util.JwtOperator;
import io.jsonwebtoken.Claims;
import java.lang.reflect.Method;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
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
public class AuthAspect {

  @Autowired
  private JwtOperator jwtOperator;

  @Around("@annotation(com.itmuch.contentcenter.auth.CheckLogin)")
  public Object checkLogin(ProceedingJoinPoint point) throws Throwable {
    checkToken();
    return point.proceed();
  }

  private void checkToken() {
    //1. 从header里获取token
    HttpServletRequest request = getHttpServletRequest();

    String token = request.getHeader("X-Token");
    //2. 校验token是否合法并且是否过期，如果不合法直接抛异常；如果合法，直接放行
    Boolean isValid = jwtOperator.validateToken(token);
    if (!isValid) {
      throw new SecurityException("Token不合法！");
    }
    //3. 如果校验成功，那么将用户信息设置到request的attribute里面,claims是设置的用户信息
    Claims claims = jwtOperator.getClaimsFromToken(token);
    request.setAttribute("id", claims.get("id"));
    request.setAttribute("role", claims.get("role"));
  }

  private HttpServletRequest getHttpServletRequest() {
    RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
    return attributes.getRequest();
  }

  @Around("@annotation(com.itmuch.contentcenter.auth.CheckAuthorization)")
  public Object checkAuthorization(ProceedingJoinPoint point) throws Throwable {
    try {
      //1.验证token是否合法
      checkToken();
      HttpServletRequest request = getHttpServletRequest();
      String role = (String) request.getAttribute("role");
      MethodSignature signature = (MethodSignature) point.getSignature();
      Method method = signature.getMethod();
      CheckAuthorization annotation = method.getAnnotation(CheckAuthorization.class);
      String value = annotation.value();
      if (Objects.equals(role, value)) {
        throw new SecurityException("用户无权访问！");
      }
    } catch (SecurityException e) {
      throw new SecurityException("用户无权访问！");
    }
    return point.proceed();
  }
}
