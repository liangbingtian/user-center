package com.itmuch.contentcenter.service.impl;

import io.mybatis.service.AbstractService;

import com.itmuch.contentcenter.service.MidUserShareService;
import com.itmuch.contentcenter.mapper.MidUserShareMapper;
import com.itmuch.contentcenter.model.MidUserShare;
import org.springframework.stereotype.Service;

/**
 * mid_user_share - 用户-分享中间表【描述用户购买的分享】
 *
 * @author liangbingtian
 */
@Service
public class  MidUserShareServiceImpl extends AbstractService<MidUserShare, Long, MidUserShareMapper> implements MidUserShareService {

}
