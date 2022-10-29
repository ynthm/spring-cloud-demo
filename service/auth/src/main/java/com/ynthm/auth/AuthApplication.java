package com.ynthm.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ethan Wang
 */
@Slf4j
@SpringBootApplication
public class AuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(AuthApplication.class, args);
    log.info("==========(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ ==========");
  }
}
