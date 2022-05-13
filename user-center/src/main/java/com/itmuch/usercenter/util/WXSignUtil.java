package com.itmuch.usercenter.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 微信签名验证工具
 *
 * @author liangbingtian
 * @date 2022/05/13 下午4:06
 */
public class WXSignUtil {

  //验证的token，和公众号配置的需一致
  private static final String token = "liangbingtian";

  /**
   * 校验签名
   *
   * @param signature 签名
   * @param timestamp 时间戳
   * @param nonce     随机数
   * @return 布尔
   */
  public static boolean checkSignature(String signature, String timestamp, String nonce) {
    String checktext = null;
    if (null != signature) {
      String[] paramArr = new String[]{token, timestamp, nonce};
      Arrays.sort(paramArr);
      String content = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);
      try {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(content.getBytes());
        checktext = byteToStr(digest);
      } catch (NoSuchAlgorithmException e) {
        throw new RuntimeException(e.getMessage());
      }
    }
    //将加密后的字符串与signature进行对比
    return checktext != null && checktext.equals(signature.toUpperCase());
  }


  /**
   * 将字节数组转化为16进制字符串
   *
   * @param byteArrays 字节数组
   * @return 字符串
   */
  private static String byteToStr(byte[] byteArrays) {
    StringBuilder str = new StringBuilder();
    for (byte b : byteArrays) {
      str.append(byteToHexStr(b));
    }
    return str.toString();
  }

  private static String byteToHexStr(byte mByte) {
    char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    char[] tampArr = new char[2];
    tampArr[0] = Digit[(mByte >>> 4) & 0X0F];
    tampArr[1] = Digit[mByte & 0X0F];
    return new String(tampArr);
  }
}
