package com.ynthm.infra.api;

import com.ynthm.common.domain.Result;
import com.ynthm.infra.api.dto.*;
import com.ynthm.infra.api.factory.SmsApiFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static com.ynthm.infra.api.Constant.CID_MESSAGE;
import static com.ynthm.infra.api.Constant.SERVICE_INFRA;

/**
 * 短信服务接口
 *
 * @author Ethan Wang
 */
@FeignClient(
    value = SERVICE_INFRA,
    contextId = CID_MESSAGE,
    fallbackFactory = SmsApiFallbackFactory.class)
public interface MessageApi {

  /**
   * 发送短信
   *
   * @param req 请求参数
   * @return
   * @throws Exception
   */
  @PostMapping("/sms/send")
  Result<String> send(@Validated @RequestBody SendSmsReq req);

  /**
   * 批量发送短信
   *
   * @param req
   * @return
   */
  @PostMapping("/sms/sendBatch")
  Result<String> sendBatch(@Validated @RequestBody SmsSendBatchReq req);

  /**
   * 发送邮件
   *
   * @param req 请求参数
   * @return
   */
  @PostMapping("/mail/send")
  Result<String> sendEmail(@Validated @RequestBody SendMailReq req);

  /**
   * SMTP 发送邮件
   *
   * @param req
   * @return
   */
  @PostMapping("/mail/smtp/send")
  Result<Void> sendAttachmentMail(@Validated @RequestBody SendAttachedMailReq req);

  @PostMapping("/mail/smtp/send/attached")
  Result<Void> sendAttachmentMail(
      @Validated @RequestPart("message") SendAttachedMailReq req,
      @RequestPart("file") MultipartFile[] files);

  /**
   * 发送验证码
   *
   * @param request 请求参数
   * @return
   */
  @PostMapping("/code/sms")
  Result<Void> sendCaptcha(@RequestBody @Valid SendPhoneCaptchaReq request);
}
