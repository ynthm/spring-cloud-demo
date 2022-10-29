package com.ynthm.demo.infrastructure.util;

import com.ynthm.common.exception.BaseException;
import com.ynthm.infra.api.dto.SendAttachedMailReq;
import com.ynthm.infra.api.enums.InfraResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Ethan Wang
 */
@Slf4j
@Component
public class JavaMailUtil {

  private final MailProperties mailProperties;

  private final JavaMailSender mailSender;

  public JavaMailUtil(MailProperties mailProperties, JavaMailSender mailSender) {
    this.mailProperties = mailProperties;
    this.mailSender = mailSender;
  }

  /**
   * 发送纯文本形式的 email
   *
   * @param simpleMailMessage 简单邮件消息
   */
  public void sendTextMail(SimpleMailMessage simpleMailMessage) {
    simpleMailMessage.setFrom(mailProperties.getUsername());
    simpleMailMessage.setSentDate(new Date());
    mailSender.send(simpleMailMessage);
  }

  /**
   * 发送带有html的内容
   *
   * @param req 邮件消息
   */
  public void sendHtmlMail(SendAttachedMailReq req) {
    MimeMessage msg = mailSender.createMimeMessage();
    MimeMessageHelper helper;
    try {
      helper = new MimeMessageHelper(msg, req.isMultipart(), StandardCharsets.UTF_8.name());
      // 邮件发件人
      helper.setFrom(mailProperties.getUsername());
      helper.setTo(req.getTo().toArray(new String[req.getTo().size()]));
      helper.setSubject(req.getSubject());
      helper.setText(req.getContent(), req.isHtml());
      if (req.isMultipart()) {
        List<MultipartFile> attachmentFileList = req.getAttachmentFileList();
        if (Objects.nonNull(attachmentFileList)) {
          for (MultipartFile multipartFile : attachmentFileList) {
            String originalFilename = multipartFile.getOriginalFilename();
            if (Objects.nonNull(originalFilename)) {
              helper.addAttachment(originalFilename, multipartFile);
            }
          }
        }
      }

      mailSender.send(msg);
    } catch (MessagingException e) {
      throw new BaseException(InfraResultCode.S_INFRA_ERROR);
    }
  }
}
