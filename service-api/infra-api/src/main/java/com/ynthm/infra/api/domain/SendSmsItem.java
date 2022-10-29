package com.ynthm.infra.api.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ynthm.common.exception.BaseException;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 短信发送必要信息
 *
 * @author Ethan Wang
 */
@Accessors(chain = true)
@Data
public class SendSmsItem {
  /** 短信接收号码 */
  @Min(0)
  @NotBlank
  private String phoneNumber;

  /** SDK 注册签名 */
  private String signName;

  /** 占位符及对应值列表 */
  private List<PlaceholderAndValue> placeholder2ValueList;

  public Map<String, String> getTemplateParam() {
    return placeholder2ValueList.stream()
        .collect(
            Collectors.toMap(
                PlaceholderAndValue::getPlaceholder,
                PlaceholderAndValue::getValue,
                (o1, o2) -> o1));
  }

  public String templateParamMap(ObjectMapper objectMapper) {
    String s = null;
    try {
      if (placeholder2ValueList != null) {
        s =
            objectMapper.writeValueAsString(
                placeholder2ValueList.stream()
                    .collect(
                        Collectors.toMap(
                            PlaceholderAndValue::getPlaceholder,
                            PlaceholderAndValue::getValue,
                            (o1, o2) -> o1)));
      }
    } catch (JsonProcessingException e) {
      throw new BaseException(e);
    }

    return s;
  }
}
