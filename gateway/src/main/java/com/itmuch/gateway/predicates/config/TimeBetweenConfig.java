package com.itmuch.gateway.predicates.config;

import java.time.LocalTime;
import lombok.Data;

/**
 * @author liangbingtian
 * @date 2022/05/16 下午7:22
 */
@Data
public class TimeBetweenConfig {

  private LocalTime start;
  private LocalTime end;

}
