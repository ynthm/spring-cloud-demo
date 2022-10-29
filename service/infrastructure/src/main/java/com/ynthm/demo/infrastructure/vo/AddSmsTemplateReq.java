package com.ynthm.demo.infrastructure.vo;

import com.ynthm.demo.infrastructure.enums.SmsTemplateType;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 新增短信模板请求
 *
 * @author Ethan Wang
 */
@Data
public class AddSmsTemplateReq {

  @NotNull private SmsTemplateType type;

  @NotBlank
  @Length(max = 30)
  private String name;

  @NotBlank
  @Length(max = 500)
  private String content;

  @NotBlank
  @Length(max = 100)
  private String remark;
}
