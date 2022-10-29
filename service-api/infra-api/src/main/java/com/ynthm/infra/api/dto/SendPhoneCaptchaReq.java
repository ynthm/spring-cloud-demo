package com.ynthm.infra.api.dto;

import com.ynthm.infra.api.domain.PhoneNumber;
import com.ynthm.infra.api.enums.CaptchaScopeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Ethan Wang
 */
@Data
@Accessors(chain = true)
public class SendPhoneCaptchaReq {

  @Valid @NotNull private PhoneNumber phoneNumber;

  /** 默认 zh-CN */
  private String lang;

  @NotNull private CaptchaScopeEnum captchaScope;
}
