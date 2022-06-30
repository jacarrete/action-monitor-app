package com.betvictor.listenerservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.impossibl.postgres.jdbc.PGDataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public PGDataSource getDataSource() {
        PGDataSource dataSource = new PGDataSource();
        dataSource.setHost("postgresqldb");
        dataSource.setPort(5432);
        dataSource.setDatabase("messages");
        dataSource.setUser("postgres");
        dataSource.setPassword("password");
        return dataSource;
    }

}
