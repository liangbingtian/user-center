package com.itmuch.usercenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.itmuch.usercenter.config.WXRequestConfig;
import com.itmuch.usercenter.dto.wx.WXDecryptMsgDTO;
import com.itmuch.usercenter.dto.wx.WXEncryptMsgDTO;
import com.itmuch.usercenter.dto.wx.WXTicketRequest;
import com.itmuch.usercenter.dto.wx.WXTicketResponse;
import com.itmuch.usercenter.dto.wx.WXTokenResponse;
import com.itmuch.usercenter.service.IWenhaiWXService;
import com.itmuch.usercenter.util.ApiCaller;
import com.itmuch.usercenter.util.wx.AesException;
import com.itmuch.usercenter.util.wx.WXBizMsgCrypt;
import java.io.StringReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 预警模块调用微信服务的相关方法
 *
 * @author liangbingtian
 * @date 2022/05/12 下午6:00
 */
@Service
@Slf4j
public class WenhaiWXServiceImpl implements IWenhaiWXService {

  @Autowired
  private StringRedisTemplate template;

  @Autowired
  private WXRequestConfig wxRequestConfig;

  @Autowired
  private ApiCaller apiCaller;

  @Autowired
  private WxMpService wxMpService;


  /**
   * 获取请求微信公众号需要的accessToken
   *
   * @return accessToken
   */
  public String getAccessToken() {
    String access_token_key = KEY_MODULE_NAME + ACCESS_TOKEN_KEY;
    String accessToken = template.opsForValue().get(access_token_key);
    if (StringUtils.isNotEmpty(accessToken)) {
      //accessToken还未过期，直接返回
      return accessToken;
    }
    //如果token已经过期，发送请求，获取token
    WXTokenResponse response = apiCaller
        .getJsonWithType(wxRequestConfig.getRequestTokenUrl(), WXTokenResponse.class,
            wxRequestConfig.getAppId(), wxRequestConfig.getAppSecret());
    if (StringUtils.isNotEmpty(response.getErrCode()) && !SUCCESS_CODE
        .equals(response.getErrCode())) {
      log.error("请求access_token失败,失败编码为:{},失败原因为:{}", response.getErrCode(), response.getErrMsg());
      throw new RuntimeException(response.getErrMsg());
    }
    template.opsForValue().set(access_token_key, response.getAccessToken(), response.getExpiresIn(),
        TimeUnit.SECONDS);
    return response.getAccessToken();
  }

  public String getAccessToken1() {
    try {
      return wxMpService.getAccessToken();
    } catch (WxErrorException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  /**
   * 请求换取带参数二维码需要的ticket
   *
   * @param info 参数信息
   * @return ticket
   */
  public String getQrCodeTicket(String info) {
    String qrcode_ticket_key = KEY_MODULE_NAME + QRCODE_TICKET_KEY + info;
    //查看ticket是否已经请求过，如果已经请求过了，直接获取
    String ticket = template.opsForValue().get(qrcode_ticket_key);
    if (StringUtils.isNotEmpty(ticket)) {
      WXTicketResponse response = JSON.parseObject(ticket, WXTicketResponse.class);
      return response.getTicket();
    }
    //如果没请求过，则重新获取
    //获取accessToken
    String accessToken = getAccessToken();
    //请求ticket接口
    WXTicketRequest request = new WXTicketRequest();
    request.setExpireSeconds(QR_CODE_EXPIRE_SECONDS);
    request.setActionName(TEMP_QR_CODE);
    Map<String, Object> actionInfo = new HashMap<>();
    Map<String, Object> scene = new HashMap<>();
    scene.put(SCENE_STR, info);
    actionInfo.put(SCENE, scene);
    request.setActionInfo(actionInfo);
    String requestBody = JSON.toJSONString(request);
    log.info("根据access_token获取ticket，传递的请求体为:{}", requestBody);
    //发送请求
    WXTicketResponse response = apiCaller
        .postJsonWithType(wxRequestConfig.getRequestTicketUrl(), requestBody,
            WXTicketResponse.class, accessToken);
    //测试中遇到了有效期内也产生了失效的问题
    if (TOKEN_INVALID.equals(response.getErrCode())) {
      String accessTokenKey = KEY_MODULE_NAME + ACCESS_TOKEN_KEY;
      template.delete(accessTokenKey);
      throw new RuntimeException("报错:" + response.getErrMsg() + ",请重新请求access_token");
    }
    template.opsForValue()
        .set(qrcode_ticket_key, JSON.toJSONString(response), response.getExpireSeconds(),
            TimeUnit.SECONDS);
    return response.getTicket();
  }

  public String getQrCodeTicket1(String info) {
    try {
      WxMpQrCodeTicket ticket = wxMpService.getQrcodeService().qrCodeCreateTmpTicket(info, 3000);
      return ticket.getTicket();
    } catch (WxErrorException e) {
      throw new RuntimeException(e.getMessage());
    }

  }

  /**
   * 使用ticket换取QRCode的url。前端进行渲染
   *
   * @return
   */
  public String showQrCode(String info) {
    try {
      info = URLDecoder.decode(info, "utf-8");
      //获取ticket
      String qrCodeTicket = getQrCodeTicket1(info);
      return wxMpService.getQrcodeService().qrCodePictureUrl(qrCodeTicket);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }


  @Override
  public boolean checkSignature(String timestamp, String nonce, String signature) {
    return wxMpService.checkSignature(timestamp, nonce, signature);
  }

  @Override
  public void receiveWXCallback(WXEncryptMsgDTO msgDTO) {
    try {
      String encryptMsg = msgDTO.getEncrypt();
      WxMpConfigStorage storage = wxMpService.getWxMpConfigStorage();
      WXBizMsgCrypt pc = new WXBizMsgCrypt(storage.getToken(), storage.getAesKey(), storage.getAppId());
      String decryptMsg = pc.decrypt(encryptMsg);
      WXDecryptMsgDTO decryptMsgDTO = processServiceMsg(decryptMsg);
      String event = decryptMsgDTO.getEvent();
      String eventKey = decryptMsgDTO.getEventKey();
      log.debug("本次微信回调的事件为:{}, 可能存在的场景值为:{}", event, eventKey);
    } catch (AesException e) {
      throw new RuntimeException(e.getMessage());
    }
  }


  /**
   * 将xml转换为消息实体
   *
   * @param xml
   * @return
   */
  private WXDecryptMsgDTO processServiceMsg(String xml) {
    try {
      JAXBContext context = JAXBContext.newInstance(WXDecryptMsgDTO.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      StringReader stringReader = new StringReader(xml);
      return (WXDecryptMsgDTO) unmarshaller.unmarshal(stringReader);
    } catch (JAXBException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

}
