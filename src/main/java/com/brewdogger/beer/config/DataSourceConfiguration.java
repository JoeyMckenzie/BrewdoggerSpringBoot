package com.brewdogger.beer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean(name = "postgresDataSource")
    @ConfigurationProperties("spring.datasource")
    @Primary
    public DataSource postgresDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "h2DataSource")
    @ConfigurationProperties("h2.datasource")
    public DataSource h2tDataSource() {
        return DataSourceBuilder.create().build();
    }
}
