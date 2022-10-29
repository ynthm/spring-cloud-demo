package com.ynthm.demo.infrastructure.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 模板审核状态
 *
 * @author Ethan Wang
 */
@Getter
public enum TemplateApprovalStatus {

  /**
   * 0：审核中。
   */
  INIT(0),
  /**
   * 1：审核通过。
   */
  APPROVED(1),
  /**
   * 2：审核失败，请在返回参数Reason中查看审核失败原因。
   */
  FAILED(2),
  /**
   * 10：取消审核
   */
  CANCEL(10),
  ;

  TemplateApprovalStatus(int value) {
    this.value = value;
  }

  @JsonValue
  @EnumValue
  private final int value;
}
