package com.ynthm.demo.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 短信服务配置
 *
 * @author: Ethan Wang
 */
@ConfigurationProperties(prefix = "sms")
@Data
public class SmsProperties {
  /**
   * 区域
   */
  private String regionId;
  /**
   * host服务器应用key
   */
  private String appKeyId;
  /**
   * host服务器密钥
   */
  private String secret;
  /**
   * 短信签名
   */
  private String signName;
  /**
   * 短信类型
   */
  private String smsType;
  /**
   * 短信验证码过期时间 默认5分钟
   */
  private Integer expireTime = 5 * 60 * 1000;

  /**
   * 是否开启固定验证码
   */
  private Boolean enableFixedVerifyCode;

  /**
   * 固定验证码
   */
  private String fixedVerifyCode;
}
