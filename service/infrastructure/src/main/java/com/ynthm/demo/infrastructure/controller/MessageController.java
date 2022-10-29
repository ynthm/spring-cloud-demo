package com.ynthm.demo.infrastructure.controller;

import com.ynthm.common.domain.Result;
import com.ynthm.demo.infrastructure.service.SendVerificationCodeService;
import com.ynthm.demo.infrastructure.service.SmsService;
import com.ynthm.demo.infrastructure.util.JavaMailUtil;
import com.ynthm.demo.infrastructure.util.MailUtil;
import com.ynthm.infra.api.MessageApi;
import com.ynthm.infra.api.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 短信服务 Controller
 *
 * @author Ethan Wang
 */
@RestController
public class MessageController implements MessageApi {

  private final SmsService smsService;
  private final MailUtil mailUtil;

  private SendVerificationCodeService sendVerificationCodeService;

  private JavaMailUtil javaMailUtil;

  @Autowired
  public void setSmtpUtil(JavaMailUtil javaMailUtil) {
    this.javaMailUtil = javaMailUtil;
  }

  @Autowired
  public void setSendVerificationCodeService(
      SendVerificationCodeService sendVerificationCodeService) {
    this.sendVerificationCodeService = sendVerificationCodeService;
  }

  public MessageController(SmsService smsService, MailUtil mailUtil) {
    this.smsService = smsService;
    this.mailUtil = mailUtil;
  }

  /** 发送短信 */
  @Override
  public Result<String> send(SendSmsReq req) {
    return smsService.send(req);
  }

  @Override
  public Result<String> sendBatch(SmsSendBatchReq req) {
    return smsService.sendBatch(req);
  }

  @Override
  public Result<String> sendEmail(SendMailReq req) {
    return mailUtil.send(req.getItem(), req.getContent());
  }

  @Override
  public Result<Void> sendAttachmentMail(SendAttachedMailReq req) {
    javaMailUtil.sendHtmlMail(req);
    return Result.ok();
  }

  @Override
  public Result<Void> sendAttachmentMail(SendAttachedMailReq req, MultipartFile[] files) {
    req.setAttachmentFileList(
        Optional.ofNullable(files)
            .map(i -> Arrays.stream(i).collect(Collectors.toList()))
            .orElse(null));
    javaMailUtil.sendHtmlMail(req);
    return Result.ok();
  }

  @Override
  public Result<Void> sendCaptcha(SendPhoneCaptchaReq request) {
    return sendVerificationCodeService.sendCaptcha(request);
  }
}
