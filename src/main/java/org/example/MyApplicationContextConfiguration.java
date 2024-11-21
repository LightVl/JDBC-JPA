package org.example;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyApplicationContextConfiguration {

    @Bean
    public DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("postgres");
        dataSource.setPassword("04051986");
        dataSource.setURL("jdbc:postgresql://localhost:5432/postgres");
        return dataSource;
    }

    /*@Bean
    public UserDao userDao() { // (1)
        return new UserDao(dataSource());
    }*/

}
