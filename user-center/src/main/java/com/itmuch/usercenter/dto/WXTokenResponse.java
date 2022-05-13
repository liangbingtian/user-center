package com.itmuch.usercenter.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 调用请求微信access_token接口所返回的信息
 *
 * @author liangbingtian
 * @date 2022/05/12 下午5:49
 */
public class WXTokenResponse {

  @JSONField(name = "access_token")
  String accessToken;

  @JSONField(name = "expires_in")
  Long expiresIn;

  @JSONField(name = "errcode")
  String errCode;

  @JSONField(name = "errmsg")
  String errMsg;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public Long getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(Long expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String getErrCode() {
    return errCode;
  }

  public void setErrCode(String errCode) {
    this.errCode = errCode;
  }

  public String getErrMsg() {
    return errMsg;
  }

  public void setErrMsg(String errMsg) {
    this.errMsg = errMsg;
  }
}
