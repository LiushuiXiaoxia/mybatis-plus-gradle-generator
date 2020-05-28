package cn.mycommons.springdemo.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfiguration {

    @Bean
    public GlobalConfig globalConfig() {
        return new GlobalConfig();
    }
}