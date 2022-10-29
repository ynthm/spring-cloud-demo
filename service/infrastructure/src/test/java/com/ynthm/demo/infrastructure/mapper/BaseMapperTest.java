package com.ynthm.demo.infrastructure.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

/**
 * , DynamicDataSourceAutoConfiguration.class , exclude = DataSourceAutoConfiguration.class
 *
 * @author Ethan Wang
 */
@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BaseMapperTest {}
