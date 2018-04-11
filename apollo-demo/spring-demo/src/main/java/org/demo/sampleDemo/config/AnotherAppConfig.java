package org.demo.sampleDemo.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableApolloConfig(value = "TEST1.apollo", order = 11)
public class AnotherAppConfig {
}
