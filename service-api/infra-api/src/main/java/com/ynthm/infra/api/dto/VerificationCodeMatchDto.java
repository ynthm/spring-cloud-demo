package com.ynthm.infra.api.dto;

import com.ynthm.infra.api.enums.CaptchaScopeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Ethan Wang
 */
@Accessors(chain = true)
@Data
public class VerificationCodeMatchDto {

  @NotNull private CaptchaScopeEnum captchaScope;

  /** 发送验证码时的唯一标识 账户号/手机号/邮箱地址 */
  @NotBlank private String userFlag;
  /** 验证码 */
  @NotBlank private String code;
}
