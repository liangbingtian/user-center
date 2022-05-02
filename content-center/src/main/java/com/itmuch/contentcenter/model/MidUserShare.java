package com.itmuch.contentcenter.model;

import io.mybatis.provider.Entity;
import org.apache.ibatis.type.JdbcType;


/**
 * mid_user_share - 用户-分享中间表【描述用户购买的分享】
 *
 * @author liangbingtian
 */
@Entity.Table(value = "mid_user_share", remark = "用户-分享中间表【描述用户购买的分享】", autoResultMap = true)
public class MidUserShare {
  @Entity.Column(value = "id", id = true, remark = "", updatable = false, insertable = false)
  private Integer id;

  @Entity.Column(value = "share_id", remark = "share.id")
  private Integer shareId;

  @Entity.Column(value = "user_id", remark = "user.id")
  private Integer userId;


  /**
   * 获取 
   *
   * @return id - 
   */
  public Integer getId() {
    return id;
  }

  /**
   * 设置
   *
   * @param id 
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * 获取 share.id
   *
   * @return shareId - share.id
   */
  public Integer getShareId() {
    return shareId;
  }

  /**
   * 设置share.id
   *
   * @param shareId share.id
   */
  public void setShareId(Integer shareId) {
    this.shareId = shareId;
  }

  /**
   * 获取 user.id
   *
   * @return userId - user.id
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * 设置user.id
   *
   * @param userId user.id
   */
  public void setUserId(Integer userId) {
    this.userId = userId;
  }

}
