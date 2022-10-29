package com.ynthm.infra.api.domain;

import javax.validation.constraints.NotBlank;

/**
 * @author Ethan Wang
 */
public class MailContent {
  /** 邮件主题 */
  @NotBlank private String subject;
  /** 正文 带占位符 */
  @NotBlank private String body;
  /** true html; false text 默认 false */
  private Boolean html = false;

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public boolean isHtml() {
    return html;
  }

  public void setHtml(boolean html) {
    this.html = html;
  }
}
