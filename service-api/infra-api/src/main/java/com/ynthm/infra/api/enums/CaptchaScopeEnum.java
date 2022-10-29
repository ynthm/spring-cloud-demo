package com.ynthm.infra.api.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Ethan Wang
 */
public enum CaptchaScopeEnum {
  /** 注册 */
  REGISTER(0, "captcha:reg:"),
  FORGET_PASSWORD(1, "captcha:f:pwd:"),

  PHONE_LOGIN(2, "phone:login");

  CaptchaScopeEnum(int scope, String cacheCode) {
    this.scope = scope;
    this.cacheCode = cacheCode;
  }

  @JsonValue private final int scope;
  private final String cacheCode;

  public int getScope() {
    return scope;
  }

  public String getCacheCode() {
    return cacheCode;
  }
}
