package com.ynthm.infra.api.dto;

import com.ynthm.infra.api.domain.SendSmsItem;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 短信批量发送请求参数
 *
 * @author Ethan Wang
 */
@Data
public class SmsSendBatchReq {

  /** 短信模板 */
  @NotBlank private String templateCode;

  /** 发送相关信息的列表 */
  @Valid @NotEmpty private List<SendSmsItem> list;
}
