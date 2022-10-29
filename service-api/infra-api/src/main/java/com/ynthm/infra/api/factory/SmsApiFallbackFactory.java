package com.ynthm.infra.api.factory;

import com.ynthm.common.domain.Result;
import com.ynthm.infra.api.MessageApi;
import com.ynthm.infra.api.dto.*;
import com.ynthm.infra.api.enums.InfraResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 短信服务降级工厂
 *
 * @author Ethan Wang
 */
@Component
public class SmsApiFallbackFactory implements FallbackFactory<MessageApi> {
  private static final Logger log = LoggerFactory.getLogger(SmsApiFallbackFactory.class);

  @Override
  public MessageApi create(Throwable cause) {
    // log.error("用户服务调用失败: {}", ExceptionUtil.getExceptionMessage(cause));
    return new MessageApi() {
      @Override
      public Result<String> send(SendSmsReq req) {
        return Result.error(InfraResultCode.S_INFRA_ERROR, cause.getMessage());
      }

      @Override
      public Result<String> sendBatch(SmsSendBatchReq req) {
        return Result.error(InfraResultCode.S_INFRA_ERROR, cause.getMessage());
      }

      @Override
      public Result<String> sendEmail(SendMailReq req) {
        return Result.error(InfraResultCode.S_INFRA_ERROR, cause.getMessage());
      }

      @Override
      public Result<Void> sendAttachmentMail(SendAttachedMailReq req) {
        return Result.error(InfraResultCode.S_INFRA_ERROR, cause.getMessage());
      }

      @Override
      public Result<Void> sendAttachmentMail(SendAttachedMailReq req, MultipartFile[] files) {
        return Result.error(InfraResultCode.S_INFRA_ERROR, cause.getMessage());
      }

      @Override
      public Result<Void> sendCaptcha(SendPhoneCaptchaReq request) {
        return Result.error(InfraResultCode.S_INFRA_ERROR, cause.getMessage());
      }
    };
  }
}
