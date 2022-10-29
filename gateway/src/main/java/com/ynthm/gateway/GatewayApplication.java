package com.ynthm.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ethan Wang
 */
@Slf4j
@SpringBootApplication
public class GatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayApplication.class, args);
    log.info("==========(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ ==========");
  }
}
