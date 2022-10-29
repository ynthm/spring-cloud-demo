package com.ynthm.demo.infrastructure.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ynthm.common.mybatis.plus.domain.BaseEntity;
import com.ynthm.demo.infrastructure.enums.SmsTemplateType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Ethan Wang
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("infra_sms_template")
public class SmsTemplate extends BaseEntity {

  @TableId(type = IdType.AUTO)
  private Integer id;

  /** 短信类型 */
  private SmsTemplateType type;

  /** 模板名称 */
  private String name;

  /** 模板内容 */
  private String content;

  /** 模板编码 */
  private String code;

  /** 模板审核状态 */
  private Integer status;

  /** 审核备注 */
  private String reason;

  /** 备注 */
  private String remark;
}
