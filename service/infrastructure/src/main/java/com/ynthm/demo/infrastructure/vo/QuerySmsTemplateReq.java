package com.ynthm.demo.infrastructure.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 查询短信模板审核状态
 *
 * @author Ethan Wang
 */
@Data
public class QuerySmsTemplateReq {
  @NotBlank private String code;
}
