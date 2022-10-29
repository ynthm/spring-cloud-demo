package com.ynthm.demo.infrastructure.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ynthm.common.domain.Result;
import com.ynthm.demo.infrastructure.util.SmsUtil;
import com.ynthm.infra.api.dto.SendSmsReq;
import com.ynthm.infra.api.dto.SmsSendBatchReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ethan Wang
 */
@Service
public class SmsService {
  private SmsUtil smsUtil;

  private ObjectMapper objectMapper;

  @Autowired
  public void setObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Autowired
  public void setSmsUtil(SmsUtil smsUtil) {
    this.smsUtil = smsUtil;
  }

  public Result<String> send(SendSmsReq req) {
    return smsUtil.send(
        req.getSmsItem().getPhoneNumber(),
        req.getTemplateCode(),
        req.getSmsItem().templateParamMap(objectMapper));
  }

  public Result<String> sendBatch(SmsSendBatchReq req) {
    return smsUtil.send(req.getTemplateCode(), req.getList());
  }
}
