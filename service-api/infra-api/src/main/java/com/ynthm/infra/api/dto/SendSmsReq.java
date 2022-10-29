package com.ynthm.infra.api.dto;

import com.ynthm.infra.api.domain.SendSmsItem;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 短信发送请求参数
 *
 * @author Ethan Wang
 */
@Data
public class SendSmsReq {

  /** 短信模板 */
  @NotBlank private String templateCode;
  /** 发送相关信息 */
  @Valid @NotNull private SendSmsItem smsItem;
}
