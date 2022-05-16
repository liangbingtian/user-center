package com.itmuch.contentcenter.service;

import com.itmuch.contentcenter.dto.ShareAuditDTO;
import com.itmuch.contentcenter.dto.ShareDTO;
import com.itmuch.contentcenter.model.Share;


/**
 * share - 分享表
 *
 * @author liangbingtian
 */
public interface ShareService  {

  ShareDTO findShareById(Integer id);

  ShareDTO findByShareCondition(ShareDTO shareDTO);

  String getAnyIndex();

  Share auditById(Integer id, ShareAuditDTO auditDTO);

  void auditByIdInDB(Integer id, ShareAuditDTO auditDTO);

  void auditByIdWithRocketMqLog(Integer id, ShareAuditDTO auditDTO, String transactionId);

}
