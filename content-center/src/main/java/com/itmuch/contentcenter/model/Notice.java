package com.itmuch.contentcenter.model;

import io.mybatis.provider.Entity;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;

/**
 * notice 
 *
 * @author liangbingtian
 */
@Entity.Table(value = "notice", autoResultMap = true)
public class Notice {
  @Entity.Column(value = "id", id = true, remark = "id", updatable = false, insertable = false)
  private Integer id;

  @Entity.Column(value = "content", remark = "内容")
  private String content;

  @Entity.Column(value = "show_flag", remark = "是否显示 0:否 1:是")
  private Boolean showFlag;

  @Entity.Column(value = "create_time", remark = "创建时间", jdbcType = JdbcType.TIMESTAMP)
  private Date createTime;


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
   * 获取 内容
   *
   * @return content - 内容
   */
  public String getContent() {
    return content;
  }

  /**
   * 设置内容
   *
   * @param content 内容
   */
  public void setContent(String content) {
    this.content = content;
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

}
