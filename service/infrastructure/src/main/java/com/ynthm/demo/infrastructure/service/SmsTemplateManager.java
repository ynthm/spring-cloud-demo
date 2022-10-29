package com.ynthm.demo.infrastructure.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ynthm.common.domain.Result;
import com.ynthm.demo.infrastructure.entity.SmsTemplate;
import com.ynthm.demo.infrastructure.mapping.SmsTemplateMapper;
import com.ynthm.demo.infrastructure.util.SmsUtil;
import com.ynthm.demo.infrastructure.vo.AddSmsTemplateReq;
import com.ynthm.demo.infrastructure.vo.ModifySmsTemplateReq;
import com.ynthm.demo.infrastructure.vo.QuerySmsTemplateReq;
import com.ynthm.demo.infrastructure.vo.QuerySmsTemplateResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Ethan Wang
 */
@Service
public class SmsTemplateManager {

  private final ISmsTemplateService smsTemplateService;
  private SmsUtil smsUtil;

  @Autowired
  public void setSmsUtil(SmsUtil smsUtil) {
    this.smsUtil = smsUtil;
  }

  public SmsTemplateManager(ISmsTemplateService smsTemplateService) {
    this.smsTemplateService = smsTemplateService;
  }

  public Result<String> addSmsTemplate(AddSmsTemplateReq req) {
    Result<String> addSmsTemplate = smsUtil.addSmsTemplate(req);
    return Result.thenApply(
        addSmsTemplate,
        code -> {
          SmsTemplate smsTemplate = SmsTemplateMapper.INSTANCE.vo2Entity(req);
          smsTemplate.setCode(code);
          smsTemplateService.save(smsTemplate);
          return Result.ok();
        });
  }

  public Result<QuerySmsTemplateResp> querySmsTemplateReq(QuerySmsTemplateReq req) {
    Result<QuerySmsTemplateResp> result = smsUtil.querySmsTemplateReq(req);

    return Result.thenApply(
        result,
        resp -> {
          SmsTemplate entity =
              smsTemplateService.getOne(
                  Wrappers.lambdaQuery(SmsTemplate.class).eq(SmsTemplate::getCode, req.getCode()),
                  false);

          if (Objects.nonNull(entity) && !Objects.equals(entity.getStatus(), resp.getStatus())) {
            smsTemplateService.update(
                Wrappers.lambdaUpdate(SmsTemplate.class)
                    .set(SmsTemplate::getStatus, resp.getStatus())
                    .set(SmsTemplate::getReason, resp.getReason())
                    .eq(SmsTemplate::getId, entity.getId()));
          }

          return result;
        });
  }

  public Result<Void> modifySmsTemplateReq(ModifySmsTemplateReq req) {
    return Result.thenRun(
        smsUtil.modifySmsTemplateReq(req),
        () -> {
          SmsTemplate entity =
              smsTemplateService.getOne(
                  Wrappers.lambdaQuery(SmsTemplate.class).eq(SmsTemplate::getCode, req.getCode()),
                  false);
          if (Objects.nonNull(entity)) {
            entity
                .setType(req.getType())
                .setName(req.getName())
                .setContent(req.getContent())
                .setRemark(req.getRemark());
            smsTemplateService.updateById(entity);
          }
        });
  }
}
