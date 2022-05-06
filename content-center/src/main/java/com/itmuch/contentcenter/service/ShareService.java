package com.itmuch.contentcenter.service;

import com.itmuch.contentcenter.dto.ShareDTO;


/**
 * share - 分享表
 *
 * @author liangbingtian
 */
public interface ShareService  {

  ShareDTO findShareById(Integer id);

}
