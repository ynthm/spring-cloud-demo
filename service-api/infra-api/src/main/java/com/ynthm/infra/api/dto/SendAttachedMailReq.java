package com.ynthm.infra.api.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ethan Wang
 */
@Data
public class SendAttachedMailReq {

  /** 收件人地址 */
  @NotEmpty private List<@Email String> to;

  /** 抄送地址 */
  private List<@Email String> cc;

  /** 暗送地址 */
  private List<@Email String> bcc;

  /** 回复地址 */
  @Email private String replyTo;

  /** 主题 标题 */
  @NotBlank private String subject;

  @NotNull private boolean html = true;

  /** 类容 */
  @NotBlank private String content;

  @NotNull private boolean multipart = false;

  /** 附件列表 */
  private List<MultipartFile> attachmentFileList;
}
