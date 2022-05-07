package com.itmuch.usercenter.dto;


/**
 * @author liangbingtian
 * @date 2022/05/04 下午4:51
 */
public class UserDTO {

  private Integer id;

  private String wxId;

  private String wxNickname;

  private String roles;

  private String avatarUrl;

  private Integer bonus;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getWxId() {
    return wxId;
  }

  public void setWxId(String wxId) {
    this.wxId = wxId;
  }

  public String getWxNickname() {
    return wxNickname;
  }

  public void setWxNickname(String wxNickname) {
    this.wxNickname = wxNickname;
  }

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public Integer getBonus() {
    return bonus;
  }

  public void setBonus(Integer bonus) {
    this.bonus = bonus;
  }
}
