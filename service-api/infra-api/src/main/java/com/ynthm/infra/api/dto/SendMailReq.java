package com.ynthm.infra.api.dto;

import com.ynthm.infra.api.domain.MailContent;
import com.ynthm.infra.api.domain.SendMailItem;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 短信发送请求参数
 *
 * @author Ethan Wang
 */
@Data
public class SendMailReq {

  /** 收发件人及模板填充数据 */
  @Valid @NotNull private SendMailItem item;
  /** 发送相关信息 */
  @Valid @NotNull private MailContent content;
}
