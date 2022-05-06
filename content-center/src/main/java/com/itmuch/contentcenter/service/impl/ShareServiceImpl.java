package com.itmuch.contentcenter.service.impl;

import com.itmuch.contentcenter.dto.ShareDTO;
import com.itmuch.contentcenter.dto.UserDTO;

import com.itmuch.contentcenter.service.ShareService;
import com.itmuch.contentcenter.mapper.ShareMapper;
import com.itmuch.contentcenter.model.Share;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * share - 分享表
 *
 * @author liangbingtian
 */
@Service
@Slf4j
public class ShareServiceImpl implements ShareService {

  @Autowired
  private ShareMapper shareMapper;

  @Autowired
  private DiscoveryClient discoveryClient;

  @Override
  public ShareDTO findShareById(Integer id) {
    Share share = shareMapper.selectById(id);
    Integer userId = share.getUserId();
    //拿到所有实例信息
    List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
    List<String> targetUrls = instances.stream()
        .map(instance -> instance.getUri().toString() + "/users/{id}").collect(Collectors.toList());
    int i = ThreadLocalRandom.current().nextInt(targetUrls.size());
    RestTemplate restTemplate = new RestTemplate();
    log.info("请求的url地址为:{}", targetUrls.get(i));
    UserDTO userDTO = restTemplate
        .getForObject(targetUrls.get(i), UserDTO.class, userId);
    ShareDTO shareDTO = new ShareDTO();
    BeanUtils.copyProperties(share, shareDTO);
    shareDTO.setWxNickname(userDTO.getWxNickname());
    return shareDTO;
  }


}
