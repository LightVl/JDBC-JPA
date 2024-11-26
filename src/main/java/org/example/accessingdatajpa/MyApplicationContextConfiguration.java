package org.example.accessingdatajpa;

import lombok.Getter;
import lombok.Setter;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import javax.sql.DataSource;

@Configuration
@Setter
@Getter
@ConfigurationProperties(prefix = "spring.datasource")
@ConfigurationPropertiesScan
@PropertySource("classpath:application.properties")
//doesnt work without that 3 lines
public class MyApplicationContextConfiguration {

    private String url;
    private String username;
    private String password;

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setURL(url);
        return dataSource;
    }
}
