CREATE TABLE `infra_sms_template`
(
    `id`          int unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `type`        tinyint      NOT NULL COMMENT '类型',
    `name`        varchar(30)  NOT NULL COMMENT '模板名称',
    `content`     varchar(500) NOT NULL COMMENT '模板内容',
    `remark`      varchar(100) NOT NULL COMMENT '备注',
    `code`        varchar(100) NOT NULL COMMENT '模板编码',
    `status`      tinyint      NOT NULL DEFAULT '-1' COMMENT '模板审核状态',
    `reason`      varchar(255)          DEFAULT NULL COMMENT '审批备注',
    `create_by`   varchar(64)           DEFAULT NULL COMMENT '创建人',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`   varchar(64)           DEFAULT NULL COMMENT '更新人',
    `update_time` datetime              DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='短信模板';