package com.ynthm.demo.infrastructure.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ynthm.demo.infrastructure.entity.SmsTemplate;
import com.ynthm.demo.infrastructure.mapper.SmsTemplateMapper;
import com.ynthm.demo.infrastructure.service.ISmsTemplateService;
import org.springframework.stereotype.Service;

/**
 * @author Ethan Wang
 */
@Service
public class SmsTemplateServiceImpl extends ServiceImpl<SmsTemplateMapper, SmsTemplate>
    implements ISmsTemplateService {}
