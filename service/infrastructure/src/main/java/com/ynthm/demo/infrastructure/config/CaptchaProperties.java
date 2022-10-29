package com.ynthm.demo.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * @author Ethan Wang
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "sms.captcha")
public class CaptchaProperties {
  private Duration validityPeriod;
  private CaptchaTemplate template;
}
