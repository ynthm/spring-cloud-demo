package com.ynthm.demo.infrastructure.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Ethan Wang
 */
@Data
@Accessors(chain = true)
public class QuerySmsTemplateResp {
  /** 模板审核状态 */
  private Integer status;

  /** 审核备注 */
  private String reason;
}
