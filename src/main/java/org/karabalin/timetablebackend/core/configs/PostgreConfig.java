package org.karabalin.timetablebackend.core.configs;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.flyway.FlywayDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class PostgreConfig {
    @Bean
    @FlywayDataSource
    @ConfigurationProperties(prefix = "spring.datasource.postgre")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcOperations jdbcOperations(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}