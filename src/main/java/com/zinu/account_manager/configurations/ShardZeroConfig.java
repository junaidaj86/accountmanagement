package com.zinu.account_manager.configurations;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@Component
@ConfigurationProperties("spring.datasource.shard0")
public class ShardZeroConfig {

    private String url;
	private String password;
	private String username;

    
 
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // @Bean
    // @ConfigurationProperties("app.datasource.shard0")
    // public DataSourceProperties shard0DataSourceProperties() {
    //     return new DataSourceProperties();
    // }

    // @Bean
    // public DataSource shard0DataSource() {
    //     return shard0DataSourceProperties().initializeDataSourceBuilder().build();
    // }
}
