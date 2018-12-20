package cn.mycommons.springdemo.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfiguration {

    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }

    @Bean
    public GlobalConfig globalConfig(@Autowired ISqlInjector sqlInjector) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setSqlInjector(sqlInjector);
        return globalConfig;
    }
}