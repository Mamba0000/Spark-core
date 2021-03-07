package com.lyc.spark.core.redis.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(SparkCoreRedisProperties.PREFIX_KEY)
public class SparkCoreRedisProperties {

    /**
     * key
     */
    public static final String PREFIX_KEY = "spark.core.redis";
    /**
     * 是否开启Lettuce
     */
    public Boolean enable = true;

}
