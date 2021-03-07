package com.lyc.spark.core.mybatisplus.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Data
@EnableConfigurationProperties
@ConfigurationProperties(SparkCoreMybatisPlusProperties.PREFIX_KEY)
public class SparkCoreMybatisPlusProperties {
    /**
     * key
     */
    public static final String PREFIX_KEY = "spark.core.redis";
    /**
     * 是否开启
     */
    public Boolean enable = true;

}
