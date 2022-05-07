package com.itmuch.contentcenter.configuration;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.core.Balancer;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liangbingtian
 * @date 2022/05/06 下午7:51
 */
@Slf4j
public class NacosSameClusterWeightedRule extends AbstractLoadBalancerRule {

  @Autowired
  private NacosDiscoveryProperties nacosDiscoveryProperties;

  @Override
  public void initWithNiwsConfig(IClientConfig iClientConfig) {

  }

  @Override
  public Server choose(Object o) {
    try {
      String clusterName = nacosDiscoveryProperties.getClusterName();
      BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
      String name = loadBalancer.getName();
      NacosServiceManager manager = new NacosServiceManager();
      NamingService namingService = manager
          .getNamingService(nacosDiscoveryProperties.getNacosProperties());

      //1.找到指定服务名称的所有实例A
      List<Instance> instances = namingService.selectInstances(name, true);
      //2.过滤出相同集群下的所有实例B
      List<Instance> sameClusterInstances = instances.stream()
          .filter(instance -> Objects.equals(instance.getClusterName(), clusterName)).collect(
              Collectors.toList());
      //3.如果B是空，那么就用A
      List<Instance> instancesToBeChosen = new ArrayList<>();
      if (CollectionUtils.isEmpty(sameClusterInstances)) {
        //发生跨集群调用
        log.warn("发生跨集群调用,name = {}, clusterName={}, instance={}", name, clusterName, instances);
        instancesToBeChosen = instances;
      } else {
        instancesToBeChosen = sameClusterInstances;
      }
      //4.基于权重的负载均衡算法，返回一个实例。
      Instance instance = ExtendBalancer.getHostByRandomWeight2(instancesToBeChosen);
      log.info("选择的实例是 port={}, instance={}", instance.getPort(), instance);
      return new NacosServer(instance);
    } catch (NacosException e) {
      log.error("发生异常了:{}", e.getErrMsg());
      return null;
    }
  }
}

class ExtendBalancer extends Balancer {

  public static Instance getHostByRandomWeight2(List<Instance> hosts) {
    return getHostByRandomWeight(hosts);
  }
}