package com.ynthm.demo.infrastructure.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @author Ethan Wang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ModifySmsTemplateReq extends AddSmsTemplateReq {
  @NotBlank private String code;
}
