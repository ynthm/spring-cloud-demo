package com.ynthm.demo.infrastructure.config.properties;

import com.ynthm.demo.infrastructure.config.AliyunRegion;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 短信服务配置
 *
 * @author: Ethan Wang
 */
@ConfigurationProperties(prefix = "aliyun.mail")
@Data
public class AliyunMailProperties {
  /** 华东1 (杭州) */
  private AliyunRegion huaDongRegion;
  /** host服务器应用key */
  private String accessKeyId;
  /** host服务器密钥 */
  private String accessKeySecret;
}
