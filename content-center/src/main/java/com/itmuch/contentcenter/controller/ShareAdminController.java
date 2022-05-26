package com.itmuch.contentcenter.controller;

import com.itmuch.contentcenter.auth.CheckAuthorization;
import com.itmuch.contentcenter.dto.ShareAuditDTO;
import com.itmuch.contentcenter.model.Share;
import com.itmuch.contentcenter.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 分享管理控制
 *
 * @author liangbingtian
 * @date 2022/05/09 下午12:08
 */
@RestController
@RequestMapping("/admin/shares")
public class ShareAdminController {

  @Autowired
  private ShareService shareService;

  @PostMapping("/audit/{id}")
  @CheckAuthorization("admin")
  public Share auditById(@PathVariable(value = "id") Integer id, @RequestBody ShareAuditDTO shareAuditDTO) {
    return shareService.auditById(id, shareAuditDTO);
  }

  public static void main(String[] args) {
    String str = "wenhai-active-monitor-warning:wx:state:qrscene_qrscene_d297d711a5f04fbbbad591afd2c8fc1b";
    int i = str.lastIndexOf("_");
    System.out.println(str.substring(i+1));
  }
}
