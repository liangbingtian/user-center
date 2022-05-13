package com.itmuch.usercenter.controller;

import com.alibaba.nacos.client.naming.utils.SignUtil;
import com.itmuch.usercenter.dto.WXServiceMsgDTO;
import com.itmuch.usercenter.service.IWenhaiWXService;
import com.itmuch.usercenter.util.WXSignUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 处理调用微信接口相关
 *
 * @author liangbingtian
 * @date 2022/05/13 下午3:04
 */
@RestController
@RequestMapping(value = "/vx")
public class WXController {

  @Autowired
  private IWenhaiWXService wenhaiWXService;

  @GetMapping(value = "/qr-code")
  public String showQrCode(@RequestParam(value = "info") String info) {
    return wenhaiWXService.showQrCode(info);
  }

  /**
   * 真实的接收信息的接口
   *
   * @param dto
   */
  @PostMapping(value = "/call-back-nosafe")
  public void handleCallBack(@RequestBody WXServiceMsgDTO dto) {
    wenhaiWXService.receiveWXCallback(dto);
  }

  @GetMapping(value = "/call-back")
  public void checkCallBackSignature(HttpServletRequest request, HttpServletResponse response) {
    //签名
    String signature = request.getParameter("signature");
    //时间戳
    String timestamp = request.getParameter("timestamp");
    //随机数
    String nonce = request.getParameter("nonce");
    //随机字符串
    String echostr = request.getParameter("echostr");

    PrintWriter out = null;

    try {
      out = response.getWriter();
      //通过检验signature对请求进行校验，校验成功则原样返回echostr，否则接入失败
      if (WXSignUtil.checkSignature(signature, timestamp, nonce)) {
        out.print(echostr);
      }
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }

  @GetMapping(value = "/call-back-nosafe")
  public void checkCallBackWithOutSignature(HttpServletRequest request,
      HttpServletResponse response) {
    //随机字符串
    String echostr = request.getParameter("echostr");

    try (PrintWriter out = response.getWriter()) {
      //通过检验signature对请求进行校验，校验成功则原样返回echostr，否则接入失败
      out.print(echostr);
    } catch (IOException e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
