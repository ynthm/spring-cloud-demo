package com.ynthm.demo.infrastructure;

import com.ynthm.common.mybatis.plus.EnableMybatisPlus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ethan Wang
 */
@Slf4j
@EnableMybatisPlus
@SpringBootApplication
public class InfrastructureApplication {

  public static void main(String[] args) {
    SpringApplication.run(InfrastructureApplication.class, args);
    log.info("==========(♥◠‿◠)ﾉﾞ  启动成功   ლ(´ڡ`ლ)ﾞ ==========");
  }
}
