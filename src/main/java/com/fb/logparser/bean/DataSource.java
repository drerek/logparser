package com.fb.logparser.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class DataSource {

    @ConfigurationProperties(prefix = "datasource.postgres")
    @Bean
    @Primary
    public DataSource dataSource() {
        return (DataSource) DataSourceBuilder
                .create()
                .build();
    }
}
