package com.ynthm.demo.infrastructure.config;

import lombok.Data;

/**
 * @author Ethan Wang
 */
@Data
public class MailSenderAccount {
  private String address;
  /**
   * type 0 触发类邮件指注册激活、密码找回等；type 1 批量邮件指营销推广、订阅期刊等。
   */
  private Integer type;
}
