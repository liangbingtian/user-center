package com.itmuch.usercenter.service.impl;

import io.mybatis.service.AbstractService;

import com.itmuch.usercenter.service.BonusEventLogService;
import com.itmuch.usercenter.mapper.BonusEventLogMapper;
import com.itmuch.usercenter.model.BonusEventLog;
import org.springframework.stereotype.Service;

/**
 * bonus_event_log - 积分变更记录表
 *
 * @author liangbingtian
 */
@Service
public class  BonusEventLogServiceImpl extends AbstractService<BonusEventLog, Long, BonusEventLogMapper> implements BonusEventLogService {

}
