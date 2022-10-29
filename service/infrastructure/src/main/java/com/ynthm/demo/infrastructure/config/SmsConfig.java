package com.ynthm.demo.infrastructure.config;

import com.ynthm.demo.infrastructure.util.SmsUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 短信服务工具注入配置信息
 *
 * @author: Ethan Wang
 */
@EnableConfigurationProperties(SmsProperties.class)
@Configuration
public class SmsConfig {

  @Bean
  public SmsUtil smsUtil(SmsProperties smsProperties) {
    return new SmsUtil(smsProperties);
  }
}
