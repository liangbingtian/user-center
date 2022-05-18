package com.itmuch.usercenter.dto.wx;

import com.alibaba.fastjson.annotation.JSONField;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求ticket的请求体
 *
 * @author liangbingtian
 * @date 2022/05/13 上午9:37
 */
public class WXTicketRequest {

  @JSONField(name = "expire_seconds")
  private Long expireSeconds;

  @JSONField(name = "action_name")
  private String actionName;

  @JSONField(name = "action_info")
  private Map<String, Object> actionInfo;

  public Long getExpireSeconds() {
    return expireSeconds;
  }

  public void setExpireSeconds(Long expireSeconds) {
    this.expireSeconds = expireSeconds;
  }

  public String getActionName() {
    return actionName;
  }

  public void setActionName(String actionName) {
    this.actionName = actionName;
  }

  public Map<String, Object> getActionInfo() {
    return actionInfo;
  }

  public void setActionInfo(Map<String, Object> actionInfo) {
    this.actionInfo = actionInfo;
  }
}
