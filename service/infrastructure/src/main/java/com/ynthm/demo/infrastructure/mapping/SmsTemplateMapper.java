package com.ynthm.demo.infrastructure.mapping;

import com.ynthm.demo.infrastructure.entity.SmsTemplate;
import com.ynthm.demo.infrastructure.vo.AddSmsTemplateReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Ethan Wang
 */
@Mapper
public interface SmsTemplateMapper {
  SmsTemplateMapper INSTANCE = Mappers.getMapper(SmsTemplateMapper.class);

  SmsTemplate vo2Entity(AddSmsTemplateReq vo);
}
