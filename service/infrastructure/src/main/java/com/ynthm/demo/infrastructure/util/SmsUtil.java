package com.ynthm.demo.infrastructure.util;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import com.ynthm.common.domain.Result;
import com.ynthm.common.exception.BaseException;
import com.ynthm.demo.infrastructure.config.SmsProperties;
import com.ynthm.demo.infrastructure.enums.AliApiCode;
import com.ynthm.demo.infrastructure.vo.AddSmsTemplateReq;
import com.ynthm.demo.infrastructure.vo.ModifySmsTemplateReq;
import com.ynthm.demo.infrastructure.vo.QuerySmsTemplateReq;
import com.ynthm.demo.infrastructure.vo.QuerySmsTemplateResp;
import com.ynthm.infra.api.domain.SendSmsItem;
import com.ynthm.infra.api.enums.InfraResultCode;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 短信服务工具 对接阿里短信SDK
 *
 * @author Ethan Wang
 */
@Slf4j
public class SmsUtil {

  public static final String RESPONSE_BODY = "ali sms response body {}";
  private final SmsProperties smsProperties;

  private final AsyncClient client;

  private ObjectMapper objectMapper;

  @Autowired
  public void setObjectMapper(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public SmsUtil(SmsProperties smsProperties) {
    this.smsProperties = smsProperties;
    // 配置 Credentials 认证信息，包括 ak, secret, token
    StaticCredentialProvider provider =
        StaticCredentialProvider.create(
            Credential.builder()
                .accessKeyId(smsProperties.getAppKeyId())
                .accessKeySecret(smsProperties.getSecret())
                .build());

    // 配置产品 Client
    client =
        AsyncClient.builder()
            // 产品服务区域 ID
            .region(smsProperties.getRegionId())
            .credentialsProvider(provider)
            .overrideConfiguration(
                ClientOverrideConfiguration.create().setEndpointOverride("dysmsapi.aliyuncs.com"))
            .build();
  }

  /**
   * 发送短信
   *
   * @param phoneNumber 手机号码
   * @param templateCode 模板编码
   * @param templateParamJson {"name":"张三","number":"1390000****"}
   * @return 结果
   */
  public Result<String> send(String phoneNumber, String templateCode, String templateParamJson) {

    // 接口请求参数设置
    SendSmsRequest.Builder builder =
        SendSmsRequest.builder()
            .phoneNumbers(phoneNumber)
            .signName(smsProperties.getSignName())
            .templateCode(templateCode);

    if (!Strings.isNullOrEmpty(templateParamJson)) {
      builder.templateParam(templateParamJson);
    }

    SendSmsRequest request = builder.build();
    CompletableFuture<SendSmsResponse> response = client.sendSms(request);

    try {
      SendSmsResponse resp = response.get();
      SendSmsResponseBody body = resp.getBody();
      if (AliApiCode.OK.name().equals(body.getCode())) {
        return Result.ok();
      } else {
        log.info(RESPONSE_BODY, body);
        return Result.<String>error(InfraResultCode.ALI_SMS_SERVER_ERROR)
            .addError(body.getCode(), body.getMessage());
      }
    } catch (ExecutionException | InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new BaseException(InfraResultCode.ALI_SMS_SERVER_ERROR, e);
    }
  }

  /**
   * 批量发送短信
   *
   * @param templateCode 模板编码
   * @param list [{"name":"张三","number":"1390000****"}]
   * @return 结果
   */
  public Result<String> send(String templateCode, List<SendSmsItem> list) {
    int size = list.size();
    List<String> phones = new ArrayList<>(size);
    List<String> signNames = new ArrayList<>(size);
    List<Object> templateParams = new ArrayList<>(size);

    boolean hasParam = false;

    for (SendSmsItem item : list) {
      phones.add(item.getPhoneNumber());
      signNames.add(item.getSignName());
      if (!hasParam && item.getPlaceholder2ValueList() != null) {
        hasParam = true;
      }
      if (hasParam) {
        templateParams.add(item.getTemplateParam());
      }
    }
    // 接口请求参数设置
    SendBatchSmsRequest.Builder builder = SendBatchSmsRequest.builder();

    try {
      builder
          .templateCode(templateCode)
          .phoneNumberJson(objectMapper.writeValueAsString(phones))
          .signNameJson(objectMapper.writeValueAsString(signNames));

      if (hasParam) {
        builder.templateParamJson(objectMapper.writeValueAsString(templateParams));
      }
    } catch (JsonProcessingException e) {
      throw new BaseException(e);
    }

    CompletableFuture<SendBatchSmsResponse> response = client.sendBatchSms(builder.build());

    try {
      SendBatchSmsResponse resp = response.get();
      SendBatchSmsResponseBody body = resp.getBody();
      if (AliApiCode.OK.name().equals(body.getCode())) {
        return Result.ok();
      } else {
        log.info(RESPONSE_BODY, body);
        return Result.<String>error(InfraResultCode.ALI_SMS_SERVER_ERROR)
            .addError(body.getCode(), body.getMessage());
      }
    } catch (ExecutionException | InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new BaseException(InfraResultCode.ALI_SMS_SERVER_ERROR, e);
    }
  }

  public Result<String> addSmsTemplate(AddSmsTemplateReq req) {
    // Parameter settings for API request
    AddSmsTemplateRequest addSmsTemplateRequest =
        AddSmsTemplateRequest.builder()
            .templateType(req.getType().getValue())
            .templateName(req.getName())
            .templateContent(req.getContent())
            .remark(req.getRemark())
            .build();

    // Asynchronously get the return value of the API request
    CompletableFuture<AddSmsTemplateResponse> response =
        client.addSmsTemplate(addSmsTemplateRequest);
    // Synchronously get the return value of the API request
    try {
      AddSmsTemplateResponse resp = response.get();
      AddSmsTemplateResponseBody body = resp.getBody();
      if (AliApiCode.OK.name().equals(body.getCode())) {
        return Result.ok(body.getTemplateCode());
      } else {
        log.info(RESPONSE_BODY, body);
        return Result.<String>error(InfraResultCode.ALI_SMS_SERVER_ERROR)
            .addError(body.getCode(), body.getMessage());
      }
    } catch (InterruptedException | ExecutionException e) {
      Thread.currentThread().interrupt();
      throw new BaseException(InfraResultCode.ALI_SMS_SERVER_ERROR, e);
    }
  }

  public Result<QuerySmsTemplateResp> querySmsTemplateReq(QuerySmsTemplateReq req) {
    // Parameter settings for API request
    QuerySmsTemplateRequest querySmsTemplateRequest =
        QuerySmsTemplateRequest.builder().templateCode(req.getCode()).build();

    // Asynchronously get the return value of the API request
    CompletableFuture<QuerySmsTemplateResponse> response =
        client.querySmsTemplate(querySmsTemplateRequest);
    // Synchronously get the return value of the API request
    try {
      QuerySmsTemplateResponse resp = response.get();
      QuerySmsTemplateResponseBody body = resp.getBody();
      if (AliApiCode.OK.name().equals(body.getCode())) {
        return Result.ok(
            new QuerySmsTemplateResp()
                .setStatus(body.getTemplateStatus())
                .setReason(body.getReason()));
      } else {
        log.info(RESPONSE_BODY, body);
        return Result.<QuerySmsTemplateResp>error(InfraResultCode.ALI_SMS_SERVER_ERROR)
            .addError(body.getCode(), body.getReason());
      }
    } catch (InterruptedException | ExecutionException e) {
      Thread.currentThread().interrupt();
      throw new BaseException(InfraResultCode.ALI_SMS_SERVER_ERROR, e);
    }
  }

  public Result<Void> modifySmsTemplateReq(ModifySmsTemplateReq req) {

    // Parameter settings for API request
    ModifySmsTemplateRequest modifySmsTemplateRequest =
        ModifySmsTemplateRequest.builder()
            .templateType(req.getType().getValue())
            .templateCode(req.getCode())
            .templateName(req.getName())
            .templateContent(req.getContent())
            .remark(req.getRemark())
            .build();

    // Asynchronously get the return value of the API request
    CompletableFuture<ModifySmsTemplateResponse> response =
        client.modifySmsTemplate(modifySmsTemplateRequest);

    try {
      // Synchronously get the return value of the API request
      ModifySmsTemplateResponse resp = response.get();
      ModifySmsTemplateResponseBody body = resp.getBody();
      if (AliApiCode.OK.name().equals(body.getCode())) {
        return Result.ok();
      } else {
        log.info(RESPONSE_BODY, body);
        return Result.<Void>error(InfraResultCode.ALI_SMS_SERVER_ERROR)
            .addError(body.getCode(), body.getMessage());
      }
    } catch (InterruptedException | ExecutionException e) {
      Thread.currentThread().interrupt();
      throw new BaseException(InfraResultCode.ALI_SMS_SERVER_ERROR, e);
    }
  }
}
