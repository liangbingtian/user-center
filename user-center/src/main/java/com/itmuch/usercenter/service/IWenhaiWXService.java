package com.itmuch.usercenter.service;

import com.itmuch.usercenter.dto.wx.WXServiceMsgDTO;
import com.itmuch.usercenter.dto.wx.WXServiceMsgNotSafeDTO;

/**
 * 预警模块调用微信服务相关方法
 *
 * @author liangbingtian
 * @date 2022/05/12 下午5:39
 */
public interface IWenhaiWXService {

  //==============redis的key相关
  String KEY_MODULE_NAME = "wenhai-active-monitor-warning:";
  String ACCESS_TOKEN_KEY = "wx_access_token";
  String QRCODE_TICKET_KEY = "wx_qrcode_ticket:";



  //==============请求accessToken的参数相关
  String SUCCESS_CODE = "0";


  //==============请求ticket的参数相关
  //默认为5分钟
  Long QR_CODE_EXPIRE_SECONDS = 3000L;

  String TEMP_QR_CODE = "QR_STR_SCENE";

  String SCENE_STR = "scene_str";

  String SCENE = "scene";

  String TOKEN_INVALID = "40001";


  //===============ticket转换为url相关
  String TICKET = "TICKET";

  String showQrCode(String info);

  boolean checkSignature(String timestamp, String nonce, String signature);


  void receiveWXCallback(String signature, String timestamp, String nonce, String encryptMsg);
}
