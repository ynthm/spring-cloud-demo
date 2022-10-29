package com.ynthm.demo.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Ethan Wang
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "aliyun.mail.sender.domain1")
public class MailSenderProperties {
  /**
   * 账号+@+域名=发信地址
   */
  private List<MailSenderAccount> accounts;
}
