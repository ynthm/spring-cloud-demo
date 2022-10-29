package com.ynthm.demo.infrastructure.config;

import lombok.Data;

/**
 * @author Ethan Wang
 */
@Data
public class CaptchaTemplate {
  private String register;
  private String forgetPassword;
  private String phoneLogin;
}
