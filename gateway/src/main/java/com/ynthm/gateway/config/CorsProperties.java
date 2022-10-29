package com.ynthm.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Ethan Wang
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "cors")
public class CorsProperties {
  private List<String> allowedOriginPatterns;
}
