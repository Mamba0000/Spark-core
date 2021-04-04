package com.lyc.spark.core.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 统一异常处理配置
 */
@Configuration
@ComponentScan(value="com.lyc.spark.core.web.handler")
public class ExceptionConfig {
}