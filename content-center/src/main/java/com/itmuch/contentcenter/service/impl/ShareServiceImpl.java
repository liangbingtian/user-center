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
  private RestTemplate restTemplate;

  @Override
  public ShareDTO findShareById(Integer id) {
    Share share = shareMapper.selectById(id);
    Integer userId = share.getUserId();
    //ribbon把user-center转换为nacos上地址，进行负载均衡算法
    UserDTO userDTO = restTemplate
        .getForObject("http://user-center/users/{userId}", UserDTO.class, userId);
    ShareDTO shareDTO = new ShareDTO();
    BeanUtils.copyProperties(share, shareDTO);
    shareDTO.setWxNickname(userDTO.getWxNickname());
    return shareDTO;
  }


}
