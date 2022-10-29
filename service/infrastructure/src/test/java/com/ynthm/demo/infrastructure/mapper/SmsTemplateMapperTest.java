package com.ynthm.demo.infrastructure.mapper;

import com.ynthm.demo.infrastructure.entity.SmsTemplate;
import com.ynthm.demo.infrastructure.enums.SmsTemplateType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

/**
 * @author Ethan Wang
 */
class SmsTemplateMapperTest extends BaseMapperTest {

  @Resource private SmsTemplateMapper smsTemplateMapper;

  @Test
  void testInsert() {
    int insert =
        smsTemplateMapper.insert(
            new SmsTemplate()
                .setType(SmsTemplateType.CAPTCHA)
                .setName("name")
                .setContent("content")
                .setRemark("remark")
                .setCode("SMS_0001"));
    Assertions.assertThat(insert).isGreaterThanOrEqualTo(1);
  }
}
