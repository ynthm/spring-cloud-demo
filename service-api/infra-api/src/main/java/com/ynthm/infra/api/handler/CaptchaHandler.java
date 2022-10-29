package com.ynthm.infra.api.handler;

import com.ynthm.common.exception.BaseException;
import com.ynthm.common.redis.util.RedisUtil;
import com.ynthm.infra.api.dto.VerificationCodeMatchDto;
import com.ynthm.infra.api.enums.CaptchaScopeEnum;
import com.ynthm.infra.api.enums.InfraResultCode;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author Ethan Wang
 */
public class CaptchaHandler {
  private RedisUtil redisUtil;

  @Autowired
  public void setRedisUtil(RedisUtil redisUtil) {
    this.redisUtil = redisUtil;
  }

  public void sendVerificationCode(CaptchaScopeEnum captchaScope, String captcha, String userFlag) {
    redisUtil.put(captchaScope.getCacheCode() + userFlag, captcha, 10L, TimeUnit.MINUTES);
  }

  public boolean match(VerificationCodeMatchDto dto) {
    String flag = dto.getCaptchaScope().getCacheCode() + dto.getUserFlag();

    String captcha = (String) redisUtil.get(flag);
    if (Strings.isBlank(captcha)) {
      // 验证码失效
      throw new BaseException(InfraResultCode.CAPTCHA_INVALID);
    }
    redisUtil.delete(flag);

    return dto.getCode().equalsIgnoreCase(captcha);
  }
}
