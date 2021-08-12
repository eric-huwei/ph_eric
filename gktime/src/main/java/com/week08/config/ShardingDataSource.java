package com.week08.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/8/12 上午10:30
 */
@Configuration
public class ShardingDataSource {

    /*@Bean("ShardingDataSource")
    @ConfigurationProperties(prefix = "shardingsphere")
    public DataSource getDataSource() {
        return DataSourceBuilder.create().build();
    }*/
}
