package com.address.match.common.config;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;


@ConditionalOnClass(HikariDataSource.class)
@ConditionalOnMissingBean(DataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type", havingValue = "com.zaxxer.hikari.HikariDataSource", matchIfMissing = true)
public class H2DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String h2Url;

    @Value("${spring.datasource.username}")
    private String h2UserName;

    @Value("${spring.datasource.password}")
    private String h2Pwd;

    @Value("${spring.datasource.hikari.minimum-idle}")
    private int minimumIdle;

    @Value("${spring.datasource.hikari.maximum-pool-size}")
    private int maximumPoolSize;

    @Value("${spring.datasource.hikari.idle-timeout}")
    private int idleTimeout;

    @Value("${spring.datasource.hikari.max-lifetime}")
    private int maxLifetime;

    @Value("${spring.datasource.hikari.connection-timeout}")
    private int connectionTimeout;

    @Value("${spring.datasource.hikari.connection-test-query}")
    private String connectionTestQuery;


    @Bean(name = "h2")
    @Qualifier("h2")
    public DataSource h2(HikariDataSource datasource){
        datasource.setJdbcUrl(h2Url);
        datasource.setUsername(h2UserName);
        datasource.setPassword(h2Pwd);
        /** 配置初始化大小、最小、最大 */
        datasource.setMinimumIdle(minimumIdle);
        datasource.setMaximumPoolSize(maximumPoolSize);
        /**空闲连接超时时间 */
        datasource.setIdleTimeout(idleTimeout);
        /**连接最大存活时间*/
        datasource.setMaxLifetime(maxLifetime);
        /**连接超时时间：毫秒*/
        datasource.setConnectionTimeout(connectionTimeout);
        /**用于测试连接是否可用的查询语句*/
        datasource.setConnectionTestQuery(connectionTestQuery);
        return datasource;
    }
//@Bean
////@ConfigurationProperties(prefix = "spring.datasource.hikari")
////public HikariDataSource dataSource(DataSourceProperties properties) {
////    HikariDataSource dataSource = createDataSource(properties,
////            HikariDataSource.class);
////    if (StringUtils.hasText(properties.getName())) {
////        dataSource.setPoolName(properties.getName());
////    }
////    return dataSource;
////}
    @Bean(name = "h2JdbcTemplate")
    public JdbcTemplate h2JdbcTemplate(@Autowired @Qualifier("h2") DataSource h2){
        return new JdbcTemplate(h2);
    }

}
