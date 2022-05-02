package com.itmuch.contentcenter.service.impl;

import io.mybatis.service.AbstractService;

import com.itmuch.contentcenter.service.NoticeService;
import com.itmuch.contentcenter.mapper.NoticeMapper;
import com.itmuch.contentcenter.model.Notice;
import org.springframework.stereotype.Service;

/**
 * notice - 
 *
 * @author liangbingtian
 */
@Service
public class  NoticeServiceImpl extends AbstractService<Notice, Long, NoticeMapper> implements NoticeService {

}
