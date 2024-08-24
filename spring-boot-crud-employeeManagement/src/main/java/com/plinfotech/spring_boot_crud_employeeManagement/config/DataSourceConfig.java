package com.plinfotech.spring_boot_crud_employeeManagement.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private String max_pool_size;
    @Value("${spring.datasource.hikari.idle-timeout}")
    private String idle_timeout;
    @Value("${spring.datasource.hikari.max-lifetime}")
    private String max_lifetime;
    @Value("${spring.datasource.hikari.connection-timeout}")
    private String connection_timeout;
    @Value("${spring.datasource.hikari.pool-name}")
    private String  pool_name;
    @Value("${spring.datasource.hikari.register-mbeans}")
    private String register_mbeans;

    @Bean
    public DataSource dataSource(){
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setUsername(userName);
        hikariDataSource.setPassword(password);


        hikariDataSource.setMaximumPoolSize(Integer.parseInt(max_pool_size));
        hikariDataSource.setIdleTimeout(Long.parseLong(idle_timeout));
        hikariDataSource.setMaxLifetime(Long.parseLong(max_lifetime));
        hikariDataSource.setConnectionTimeout(Long.parseLong(connection_timeout));
        hikariDataSource.setPoolName(pool_name);
        hikariDataSource.setRegisterMbeans(Boolean.parseBoolean(register_mbeans));
        System.out.println("Idle Time-out========="+hikariDataSource.getHikariConfigMXBean().getIdleTimeout());
        System.out.println("max_pool_size========="+hikariDataSource.getHikariConfigMXBean().getMaximumPoolSize());

        return hikariDataSource;
    }


}
