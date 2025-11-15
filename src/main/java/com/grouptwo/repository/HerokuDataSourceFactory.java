package com.grouptwo.repository;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class HerokuDataSourceFactory extends DriverManagerDataSource {
    
    @PostConstruct
    public void init() {
        String databaseUrl = System.getenv("DATABASE_URL");
        
        if (databaseUrl != null && !databaseUrl.isEmpty()) {
            // Parse Heroku DATABASE_URL format: postgres://user:password@host:port/database
            try {
                URI dbUri = new URI(databaseUrl);
                String[] userInfo = dbUri.getUserInfo().split(":");
                String username = userInfo[0];
                String password = userInfo.length > 1 ? userInfo[1] : "";
                String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
                
                // Override with Heroku values
                setDriverClassName("org.postgresql.Driver");
                setUrl(dbUrl);
                setUsername(username);
                setPassword(password);
            } catch (URISyntaxException e) {
                throw new RuntimeException("Error parsing DATABASE_URL", e);
            }
        }
        // If DATABASE_URL is not set, use the properties set by Spring from prop.properties
    }
}

