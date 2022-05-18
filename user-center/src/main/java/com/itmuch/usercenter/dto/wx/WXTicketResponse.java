package com.itmuch.usercenter.dto.wx;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 请求ticket返回的response
 *
 * @author liangbingtian
 * @date 2022/05/13 上午10:07
 */
public class WXTicketResponse {

  private String ticket;

  @JSONField(name = "expire_seconds")
  private Long expireSeconds;

  private String url;

  @JSONField(name = "errcode")
  String errCode;

  @JSONField(name = "errmsg")
  String errMsg;

  public String getTicket() {
    return ticket;
  }

  public void setTicket(String ticket) {
    this.ticket = ticket;
  }

  public Long getExpireSeconds() {
    return expireSeconds;
  }

  public void setExpireSeconds(Long expireSeconds) {
    this.expireSeconds = expireSeconds;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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
