package com.itmuch.contentcenter.model;

import io.mybatis.provider.Entity;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * share - 分享表
 *
 * @author liangbingtian
 */
@Entity.Table(value = "share", remark = "分享表", autoResultMap = true)
public class Share {
  @Entity.Column(value = "id", id = true, remark = "id", updatable = false, insertable = false)
  private Integer id;

  @Entity.Column(value = "user_id", remark = "发布人id")
  private Integer userId;

  @Entity.Column(value = "title", remark = "标题")
  private String title;

  @Entity.Column(value = "create_time", remark = "创建时间", jdbcType = JdbcType.TIMESTAMP)
  private Date createTime;

  @Entity.Column(value = "update_time", remark = "修改时间", jdbcType = JdbcType.TIMESTAMP)
  private Date updateTime;

  @Entity.Column(value = "is_original", remark = "是否原创 0:否 1:是")
  private Boolean isOriginal;

  @Entity.Column(value = "author", remark = "作者")
  private String author;

  @Entity.Column(value = "cover", remark = "封面")
  private String cover;

  @Entity.Column(value = "summary", remark = "概要信息")
  private String summary;

  @Entity.Column(value = "price", remark = "价格（需要的积分）")
  private Integer price;

  @Entity.Column(value = "download_url", remark = "下载地址")
  private String downloadUrl;

  @Entity.Column(value = "buy_count", remark = "下载数 ")
  private Integer buyCount;

  @Entity.Column(value = "show_flag", remark = "是否显示 0:否 1:是")
  private Boolean showFlag;

  @Entity.Column(value = "audit_status", remark = "审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过")
  private String auditStatus;

  @Entity.Column(value = "reason", remark = "审核不通过原因")
  private String reason;


  /**
   * 获取 id
   *
   * @return id - id
   */
  public Integer getId() {
    return id;
  }

  /**
   * 设置id
   *
   * @param id id
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * 获取 发布人id
   *
   * @return userId - 发布人id
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * 设置发布人id
   *
   * @param userId 发布人id
   */
  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  /**
   * 获取 标题
   *
   * @return title - 标题
   */
  public String getTitle() {
    return title;
  }

  /**
   * 设置标题
   *
   * @param title 标题
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * 获取 创建时间
   *
   * @return createTime - 创建时间
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * 设置创建时间
   *
   * @param createTime 创建时间
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * 获取 修改时间
   *
   * @return updateTime - 修改时间
   */
  public Date getUpdateTime() {
    return updateTime;
  }

  /**
   * 设置修改时间
   *
   * @param updateTime 修改时间
   */
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  /**
   * 获取 是否原创 0:否 1:是
   *
   * @return isOriginal - 是否原创 0:否 1:是
   */
  public Boolean getIsOriginal() {
    return isOriginal;
  }

  /**
   * 设置是否原创 0:否 1:是
   *
   * @param isOriginal 是否原创 0:否 1:是
   */
  public void setIsOriginal(Boolean isOriginal) {
    this.isOriginal = isOriginal;
  }

  /**
   * 获取 作者
   *
   * @return author - 作者
   */
  public String getAuthor() {
    return author;
  }

  /**
   * 设置作者
   *
   * @param author 作者
   */
  public void setAuthor(String author) {
    this.author = author;
  }

  /**
   * 获取 封面
   *
   * @return cover - 封面
   */
  public String getCover() {
    return cover;
  }

  /**
   * 设置封面
   *
   * @param cover 封面
   */
  public void setCover(String cover) {
    this.cover = cover;
  }

  /**
   * 获取 概要信息
   *
   * @return summary - 概要信息
   */
  public String getSummary() {
    return summary;
  }

  /**
   * 设置概要信息
   *
   * @param summary 概要信息
   */
  public void setSummary(String summary) {
    this.summary = summary;
  }

  /**
   * 获取 价格（需要的积分）
   *
   * @return price - 价格（需要的积分）
   */
  public Integer getPrice() {
    return price;
  }

  /**
   * 设置价格（需要的积分）
   *
   * @param price 价格（需要的积分）
   */
  public void setPrice(Integer price) {
    this.price = price;
  }

  /**
   * 获取 下载地址
   *
   * @return downloadUrl - 下载地址
   */
  public String getDownloadUrl() {
    return downloadUrl;
  }

  /**
   * 设置下载地址
   *
   * @param downloadUrl 下载地址
   */
  public void setDownloadUrl(String downloadUrl) {
    this.downloadUrl = downloadUrl;
  }

  /**
   * 获取 下载数 
   *
   * @return buyCount - 下载数 
   */
  public Integer getBuyCount() {
    return buyCount;
  }

  /**
   * 设置下载数 
   *
   * @param buyCount 下载数 
   */
  public void setBuyCount(Integer buyCount) {
    this.buyCount = buyCount;
  }

  /**
   * 获取 是否显示 0:否 1:是
   *
   * @return showFlag - 是否显示 0:否 1:是
   */
  public Boolean getShowFlag() {
    return showFlag;
  }

  /**
   * 设置是否显示 0:否 1:是
   *
   * @param showFlag 是否显示 0:否 1:是
   */
  public void setShowFlag(Boolean showFlag) {
    this.showFlag = showFlag;
  }

  /**
   * 获取 审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过
   *
   * @return auditStatus - 审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过
   */
  public String getAuditStatus() {
    return auditStatus;
  }

  /**
   * 设置审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过
   *
   * @param auditStatus 审核状态 NOT_YET: 待审核 PASSED:审核通过 REJECTED:审核不通过
   */
  public void setAuditStatus(String auditStatus) {
    this.auditStatus = auditStatus;
  }

  /**
   * 获取 审核不通过原因
   *
   * @return reason - 审核不通过原因
   */
  public String getReason() {
    return reason;
  }

  /**
   * 设置审核不通过原因
   *
   * @param reason 审核不通过原因
   */
  public void setReason(String reason) {
    this.reason = reason;
  }

}
