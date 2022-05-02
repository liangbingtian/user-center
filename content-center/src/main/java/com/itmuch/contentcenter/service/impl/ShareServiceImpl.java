package com.itmuch.contentcenter.service.impl;

import io.mybatis.service.AbstractService;

import com.itmuch.contentcenter.service.ShareService;
import com.itmuch.contentcenter.mapper.ShareMapper;
import com.itmuch.contentcenter.model.Share;
import org.springframework.stereotype.Service;

/**
 * share - 分享表
 *
 * @author liangbingtian
 */
@Service
public class  ShareServiceImpl extends AbstractService<Share, Long, ShareMapper> implements ShareService {

}
