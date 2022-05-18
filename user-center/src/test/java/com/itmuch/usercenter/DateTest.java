package com.itmuch.usercenter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import org.junit.Test;

/**
 * 日期相关的测试类
 *
 * @author liangbingtian
 * @date 2022/05/16 下午8:31
 */
public class DateTest {

  @Test
  public void test1() {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
    System.out.println(dateTimeFormatter.format(LocalTime.now()));
  }
}
