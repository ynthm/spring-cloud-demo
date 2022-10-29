package com.ynthm.demo.infrastructure.service;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.ynthm.common.domain.Result;
import com.ynthm.common.util.RandomUtil;
import com.ynthm.demo.infrastructure.config.CaptchaProperties;
import com.ynthm.demo.infrastructure.config.CaptchaTemplate;
import com.ynthm.demo.infrastructure.config.SmsProperties;
import com.ynthm.infra.api.domain.PlaceholderAndValue;
import com.ynthm.infra.api.domain.SendSmsItem;
import com.ynthm.infra.api.dto.SendPhoneCaptchaReq;
import com.ynthm.infra.api.dto.SendSmsReq;
import com.ynthm.infra.api.enums.CaptchaScopeEnum;
import com.ynthm.infra.api.handler.CaptchaHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Locale;

/**
 * @author Ethan Wang
 */
@Slf4j
@Service
public class SendVerificationCodeService {

  private SmsService smsService;

  @Autowired
  public void setSmsService(SmsService smsService) {
    this.smsService = smsService;
  }

  private SmsProperties smsProperties;

  @Autowired
  public void setSmsProperties(SmsProperties smsProperties) {
    this.smsProperties = smsProperties;
  }

  private CaptchaProperties captchaProperties;

  @Autowired
  public void setCaptchaProperties(CaptchaProperties captchaProperties) {
    this.captchaProperties = captchaProperties;
  }

  private CaptchaHandler captchaHandler;

  @Resource
  public void setCaptchaHandler(CaptchaHandler captchaHandler) {
    this.captchaHandler = captchaHandler;
  }

  public Result<Void> sendCaptcha(SendPhoneCaptchaReq request) {
    String langTag = request.getLang();
    if (Strings.isNullOrEmpty(langTag)) {
      langTag = "zh-CN";
    }
    String verificationCode = RandomUtil.randomPassword();

    // 通过 captchaScope 与 lang  找到内容模板
    CaptchaScopeEnum captchaScope = request.getCaptchaScope();
    log.info("发送 locale {}", Locale.forLanguageTag(langTag));
    CaptchaTemplate template = captchaProperties.getTemplate();
    String templateCode = template.getRegister();
    if (captchaScope == CaptchaScopeEnum.FORGET_PASSWORD) {
      templateCode = template.getForgetPassword();
    } else if (CaptchaScopeEnum.PHONE_LOGIN == captchaScope) {
      templateCode = template.getPhoneLogin();
    }

    SendSmsReq sendSmsReq = new SendSmsReq();
    sendSmsReq.setTemplateCode(templateCode);
    sendSmsReq.setSmsItem(
        new SendSmsItem()
            .setSignName(smsProperties.getSignName())
            .setPhoneNumber(request.getPhoneNumber().getNumber())
            .setPlaceholder2ValueList(
                Lists.newArrayList(
                    new PlaceholderAndValue().setPlaceholder("code").setValue(verificationCode))));

    return Result.thenRun(
        smsService.send(sendSmsReq),
        () ->
            captchaHandler.sendVerificationCode(
                request.getCaptchaScope(), verificationCode, request.getPhoneNumber().getNumber()));
  }
}
