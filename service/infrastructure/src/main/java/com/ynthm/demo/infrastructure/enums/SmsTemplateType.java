package com.ynthm.demo.infrastructure.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 短信类型
 *
 * @author Ethan Wang
 */
@Getter
public enum SmsTemplateType {
  /** 0: 验证码 */
  CAPTCHA(0),
  /** 1: 短信通知 */
  NOTIFICATION(1),
  /** 2: 推广短信 */
  POPULARIZE(2),
  /** 3: 国际/港澳台消息。 */
  INTERNATIONAL(3);

  SmsTemplateType(int value) {
    this.value = value;
  }

  @JsonValue @EnumValue private final int value;
}
