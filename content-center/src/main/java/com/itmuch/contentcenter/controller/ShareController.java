package com.itmuch.contentcenter.controller;

import com.itmuch.contentcenter.dto.ShareDTO;
import com.itmuch.contentcenter.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liangbingtian
 * @date 2022/05/04 下午4:28
 */
@RestController
@RequestMapping(value = "/shares")
public class ShareController {

  @Autowired
  private ShareService shareService;

  @GetMapping(value = "/{id}")
  public ShareDTO findById(@PathVariable Integer id) {
    return shareService.findShareById(id);
  }

  @GetMapping(value = "/any-index")
  public String anyIndex() {
    return shareService.getAnyIndex();
  }
}
