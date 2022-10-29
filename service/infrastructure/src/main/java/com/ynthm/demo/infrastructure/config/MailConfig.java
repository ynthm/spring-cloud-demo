package com.ynthm.demo.infrastructure.config;

import com.ynthm.demo.infrastructure.config.properties.AliyunMailProperties;
import com.ynthm.demo.infrastructure.util.MailUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 短信服务工具注入配置信息
 *
 * @author: Ethan Wang
 */
@EnableConfigurationProperties(AliyunMailProperties.class)
@Configuration
public class MailConfig {

  @Bean
  public MailUtil mailUtil(AliyunMailProperties smsProperties) {
    return new MailUtil(smsProperties);
  }
}
