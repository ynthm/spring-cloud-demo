package com.ynthm.auth.enums;

import com.ynthm.common.enums.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Ethan
 */
@Getter
@AllArgsConstructor
public enum AuthResultCode implements ResultCode {

  /** 用户权限 */
  AUTHENTICATION_EXCEPTION(10400, "权限异常"),
  UNAUTHORIZED(10401, "未登录，请先登录。"),
  BAD_CREDENTIALS(10402, "用户名或密码错误，请确认。"),
  WRONG_PASSWORD(10403, "密码错误，请确认。"),
  USERNAME_NOT_FOUND(10404, "用户名不存在。"),

  UNKNOWN_ERROR(99999, "Unknown Error!");

  /** 返回码 */
  private final int code;
  /** 返回消息 */
  private final String message;
}
