package com.ynthm.infra.api.enums;

import com.ynthm.common.enums.ResultCode;
import lombok.AllArgsConstructor;

/**
 * @author Ethan Wang
 */
@AllArgsConstructor
public enum InfraResultCode implements ResultCode {

  /** INFRA 错误 */
  S_INFRA_ERROR(100000, "INFRA 错误"),
  CAPTCHA_INVALID(100001, "验证码失效"),

  REQUIRED_SENDER_ADDRESS(100002, "需要发件人地址"),

  ALI_SMS_SERVER_ERROR(100003, "阿里短信服务异常"),
  ;

  private final int code;
  private final String message;

  @Override
  public int getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
