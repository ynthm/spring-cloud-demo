package com.ynthm.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * 网关配置
 *
 * @author Ethan Wang
 */
@Configuration
public class GatewayConfig {
  /**
   * 解决网关跨域
   *
   * <p>@RefreshScope 无效
   *
   * @return
   */
  @Bean
  public CorsWebFilter corsWebFilter(CorsProperties corsProperties) {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration corsConfiguration = new CorsConfiguration();

    corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
    corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);

    corsConfiguration.setAllowedOriginPatterns(corsProperties.getAllowedOriginPatterns());
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.setMaxAge(3600L);

    source.registerCorsConfiguration("/**", corsConfiguration);
    return new CorsWebFilter(source);
  }
}
