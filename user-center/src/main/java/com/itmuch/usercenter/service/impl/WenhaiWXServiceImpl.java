package com.itmuch.usercenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.itmuch.usercenter.config.WXRequestConfig;
import com.itmuch.usercenter.dto.WXServiceMsgDTO;
import com.itmuch.usercenter.dto.WXTicketRequest;
import com.itmuch.usercenter.dto.WXTicketResponse;
import com.itmuch.usercenter.dto.WXTokenResponse;
import com.itmuch.usercenter.service.IWenhaiWXService;
import com.itmuch.usercenter.util.ApiCaller;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
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




  /**
   * 获取请求微信公众号需要的accessToken
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

  /**
   * 请求换取带参数二维码需要的ticket
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
      throw new RuntimeException("报错:"+response.getErrMsg()+",请重新请求access_token");
    }
    template.opsForValue()
        .set(qrcode_ticket_key, JSON.toJSONString(response), response.getExpireSeconds(),
            TimeUnit.SECONDS);
    return response.getTicket();
  }

  /**
   * 使用ticket换取QRCode的url。前端进行渲染
   * @return
   */
  public String showQrCode(String info) {
    try {
      info = URLDecoder.decode(info, "utf-8");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e.getMessage());
    }
    //获取ticket
    String qrCodeTicket = getQrCodeTicket(info);
    //替换url返回给前端
    return wxRequestConfig.getShowQrCode().replace(TICKET, qrCodeTicket);
  }

  @Override
  public void receiveWXCallback(WXServiceMsgDTO dto) {
    log.info("收到的信息为:{}", JSON.toJSONString(dto));
  }


  /**
   * 将xml转换为消息实体
   * @param xml
   * @return
   */
  private WXServiceMsgDTO processServiceMsg(String xml) {
    try {
      JAXBContext context = JAXBContext.newInstance(WXServiceMsgDTO.class);
      Unmarshaller unmarshaller = context.createUnmarshaller();
      StringReader stringReader = new StringReader(xml);
      return (WXServiceMsgDTO) unmarshaller.unmarshal(stringReader);
    } catch (JAXBException e) {
      throw new RuntimeException(e.getMessage());
    }
  }

}
