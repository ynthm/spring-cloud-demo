package com.ynthm.infra.api.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 短信发送必要信息
 *
 * @author Ethan Wang
 */
public class SendMailItem {
  /** 收件人邮箱地址 */
  @Email @NotBlank private String recipientAddress;

  /** 发件人邮箱地址 */
  @Email private String senderAddress;

  /** 占位符及对应值列表 */
  private List<PlaceholderAndValue> placeholder2ValueList;

  public String getRecipientAddress() {
    return recipientAddress;
  }

  public void setRecipientAddress(String recipientAddress) {
    this.recipientAddress = recipientAddress;
  }

  public String getSenderAddress() {
    return senderAddress;
  }

  public void setSenderAddress(String senderAddress) {
    this.senderAddress = senderAddress;
  }

  public List<PlaceholderAndValue> getPlaceholder2ValueList() {
    return placeholder2ValueList;
  }

  public void setPlaceholder2ValueList(List<PlaceholderAndValue> placeholder2ValueList) {
    this.placeholder2ValueList = placeholder2ValueList;
  }
}
