package cn.mycommons.springdemo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = MysqlConfig.PACKAGE)
public class MysqlConfig {

    static final String PACKAGE = "cn.mycommons.springdemo";

    private static final String MAPPER_LOCATION = "classpath:mapper/*.xml";

    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Autowired DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactory(@Autowired DataSource dataSource,
                                                          @Autowired GlobalConfig config) throws Exception {
        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setGlobalConfig(config);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(resolver.getResources(MAPPER_LOCATION));
        return factoryBean;
    }
}