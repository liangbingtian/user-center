package com.itmuch.usercenter.dto.wx;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * 接收微信回调消息的DTO
 *
 * @author liangbingtian
 * @date 2022/05/18 上午10:37
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class WXServiceMsgDTO {

  @XmlElement(name = "ToUserName")
  private String toUserName;

  @XmlElement(name = "Encrypt")
  private String encrypt;

  @XmlElement(name = "MsgSignature")
  private String msgSignature;

}
