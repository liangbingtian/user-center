package com.itmuch.usercenter.dto.wx;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * 公众号回调返回的DTO
 *
 * @author liangbingtian
 * @date 2022/05/13 下午2:26
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class WXServiceMsgNotSafeDTO {

  @XmlElement(name = "ToUserName")
  private String toUserName;

  @XmlElement(name = "FromUserName")
  private String fromUserName;

  @XmlElement(name = "CreateTime")
  private String createTime;

  @XmlElement(name = "MsgType")
  private String msgType;

  @XmlElement(name = "Event")
  private String event;

  @XmlElement(name = "EventKey")
  private String eventKey;

  @XmlElement(name = "ExpiredTime")
  private String expiredTime;

  @XmlElement(name = "FailTime")
  private String failTime;

  @XmlElement(name = "FailReason")
  private String failReason;


}
