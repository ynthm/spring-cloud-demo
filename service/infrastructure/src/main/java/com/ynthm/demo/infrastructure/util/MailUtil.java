package com.ynthm.demo.infrastructure.util;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dm20151123.AsyncClient;
import com.aliyun.sdk.service.dm20151123.models.SingleSendMailRequest;
import com.aliyun.sdk.service.dm20151123.models.SingleSendMailResponse;
import com.aliyun.sdk.service.dm20151123.models.SingleSendMailResponseBody;
import com.google.common.base.Strings;
import com.ynthm.common.exception.BaseException;
import com.ynthm.common.domain.Result;
import com.ynthm.demo.infrastructure.config.AliyunRegion;
import com.ynthm.demo.infrastructure.config.MailSenderAccount;
import com.ynthm.demo.infrastructure.config.MailSenderProperties;
import com.ynthm.demo.infrastructure.config.properties.AliyunMailProperties;
import com.ynthm.infra.api.domain.MailContent;
import com.ynthm.infra.api.domain.PlaceholderAndValue;
import com.ynthm.infra.api.domain.SendMailItem;
import com.ynthm.infra.api.enums.InfraResultCode;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author Ethan Wang
 */
@Slf4j
@RefreshScope
public class MailUtil {

  private final AliyunMailProperties aliyunMailProperties;
  private final StaticCredentialProvider provider;

  private MailSenderProperties mailSenderProperties;

  @Autowired
  public void setMailSenderProperties(MailSenderProperties mailSenderProperties) {
    this.mailSenderProperties = mailSenderProperties;
  }

  public MailUtil(AliyunMailProperties aliyunMailProperties) {
    this.aliyunMailProperties = aliyunMailProperties;
    // Configure Credentials authentication information, including ak, secret, token
    provider =
        StaticCredentialProvider.create(
            Credential.builder()
                .accessKeyId(aliyunMailProperties.getAccessKeyId())
                .accessKeySecret(aliyunMailProperties.getAccessKeySecret())
                .build());
  }

  public Result<String> send(SendMailItem item, MailContent mailContent) {
    AliyunRegion huaDongRegion = aliyunMailProperties.getHuaDongRegion();
    // Configure the Client
    try (AsyncClient client =
        AsyncClient.builder()
            .region(huaDongRegion.getId())
            .credentialsProvider(provider)
            .overrideConfiguration(
                ClientOverrideConfiguration.create()
                    .setEndpointOverride(huaDongRegion.getEndpoint()))
            .build()) {
      String senderAddress = item.getSenderAddress();
      if (Strings.isNullOrEmpty(senderAddress)) {
        senderAddress =
            mailSenderProperties.getAccounts().stream()
                .filter(account -> account.getType() == 1)
                .findFirst()
                .map(MailSenderAccount::getAddress)
                .orElseThrow(() -> new BaseException(InfraResultCode.REQUIRED_SENDER_ADDRESS));
      }

      // Parameter settings for API request
      SingleSendMailRequest.Builder builder =
          SingleSendMailRequest.builder()
              .accountName(senderAddress)
              .addressType(1)
              .toAddress(item.getRecipientAddress())
              .replyToAddress(false)
              .subject(mailContent.getSubject());

      String body = mailContent.getBody();
      if (Strings.isNullOrEmpty(body)) {
        if (item.getPlaceholder2ValueList() != null) {
          body =
              replacePlaceholder(
                  body,
                  item.getPlaceholder2ValueList().stream()
                      .collect(
                          Collectors.toMap(
                              PlaceholderAndValue::getPlaceholder,
                              PlaceholderAndValue::getValue,
                              (o1, o2) -> o1)));
        }

        if (mailContent.isHtml()) {
          builder.htmlBody(body);
        } else {
          builder.textBody(body);
        }
      }

      SingleSendMailRequest singleSendMailRequest = builder.build();

      // Asynchronously get the return value of the API request
      CompletableFuture<SingleSendMailResponse> response =
          client.singleSendMail(singleSendMailRequest);

      SingleSendMailResponse resp = response.get();
      SingleSendMailResponseBody respBody = resp.getBody();
      log.info("ali sms response body {}", respBody);
      return Result.ok();
    } catch (ExecutionException e) {
      throw new BaseException(InfraResultCode.ALI_SMS_SERVER_ERROR, e);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new BaseException(InfraResultCode.ALI_SMS_SERVER_ERROR, e);
    }
  }

  /**
   * 文本占位符替换
   *
   * @param template 设置文字模板 占位符 #{#username},其中#{}表示表达式的起止，#username是表达式字符串，表示引用一个变量。
   * @param params 如果表达式字符串为 #username 那么 Map 的 key 为 username
   * @return 替换占位符后的文本
   */
  public static String replacePlaceholder(String template, Map<String, String> params) {
    // 创建表达式解析器
    ExpressionParser parser = new SpelExpressionParser();
    // 解析表达式，如果表达式是一个模板表达式，需要为解析传入模板解析器上下文。
    Expression expression = parser.parseExpression(template, new TemplateParserContext());

    // 通过evaluationContext.setVariable可以在上下文中设定变量。
    SimpleEvaluationContext evaluationContext =
        SimpleEvaluationContext.forReadOnlyDataBinding().build();
    params
        .entrySet()
        .forEach(item -> evaluationContext.setVariable(item.getKey(), item.getValue()));

    return expression.getValue(evaluationContext, String.class);
  }
}
