package com.ynthm.demo.infrastructure.controller;

import com.ynthm.common.domain.Result;
import com.ynthm.demo.infrastructure.service.SmsTemplateManager;
import com.ynthm.demo.infrastructure.vo.AddSmsTemplateReq;
import com.ynthm.demo.infrastructure.vo.ModifySmsTemplateReq;
import com.ynthm.demo.infrastructure.vo.QuerySmsTemplateReq;
import com.ynthm.demo.infrastructure.vo.QuerySmsTemplateResp;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ethan Wang
 */
@RequestMapping("/sms/template")
@RestController
public class SmsTemplateController {

  private final SmsTemplateManager smsTemplateManager;

  public SmsTemplateController(SmsTemplateManager smsTemplateManager) {
    this.smsTemplateManager = smsTemplateManager;
  }

  @PostMapping("")
  public Result<String> add(@Validated @RequestBody AddSmsTemplateReq req) {
    return smsTemplateManager.addSmsTemplate(req);
  }

  @GetMapping("")
  public Result<QuerySmsTemplateResp> querySmsTemplateReq(
      @Validated @RequestBody QuerySmsTemplateReq req) {
    return smsTemplateManager.querySmsTemplateReq(req);
  }

  @PatchMapping("")
  public Result<Void> modifySmsTemplateReq(@Validated @RequestBody ModifySmsTemplateReq req) {
    return smsTemplateManager.modifySmsTemplateReq(req);
  }
}
