package com.ducnd.demo.demo2;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * Created by ducnd on 6/11/17.
 */
@Service
public class BaseManager {
    private static final Logger LOG = Logger.getLogger(BaseManager.class);
    @Autowired
    private Environment environment;

    @Bean(name = "dslContext")
    public DefaultDSLContext dsl() {
        return new DefaultDSLContext(configuration());
    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));

        return dataSource;
    }

    @Bean
    public DataSourceConnectionProvider connectionProvider() {
        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource()));
    }


    @Bean
    public DefaultConfiguration configuration() {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
        jooqConfiguration.set(connectionProvider());
//        jooqConfiguration.set(new DefaultExecuteListenerProvider(exceptionTransformer()));
        jooqConfiguration.setSQLDialect(SQLDialect.MYSQL);
        Settings settings = new Settings();
        settings.setRenderSchema(Boolean.FALSE);
        settings.setRenderCatalog(Boolean.FALSE);
        jooqConfiguration.setSettings(settings);
        return jooqConfiguration;
    }

    @Autowired
    private DSLContext dslContext;

    public DSLContext getDslContext(){
        return dslContext;
    }
}
