package com.lyc.spark.core.auth.config;

import com.lyc.spark.core.auth.aop.PreAuthAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AuthAspectConfig {

    @Bean
    public PreAuthAspect authAspect() {
        return new PreAuthAspect();
    }

}
