package ribbonconfiguration;

import com.itmuch.contentcenter.configuration.NacosSameClusterWeightedRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liangbingtian
 * @date 2022/05/06 下午5:26
 */
@Configuration
public class RibbonConfiguration {
  @Bean
  public IRule ribbonRule() {
    return new NacosSameClusterWeightedRule();
  }
}
