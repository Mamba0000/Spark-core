package com.lyc.spark.core.config;

import com.lyc.spark.core.tool.SpringUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(proxyBeanMethods = false)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ToolConfiguration implements WebMvcConfigurer {

    /**
     * Spring上下文缓存
     *
     * @return SpringUtil
     */
    @Bean
    public SpringUtil springUtil() {
        return new SpringUtil();
    }

}