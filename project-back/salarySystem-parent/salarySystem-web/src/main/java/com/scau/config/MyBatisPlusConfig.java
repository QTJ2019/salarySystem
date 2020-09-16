package com.scau.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
//@EnableTransactionManagement指的是该类具有事务处理的功能。
@Configuration
//@configurtaion 表明该类是一个配置类，springboot在启动的时候，就会根据该注解配置应用程序。
@MapperScan("com.scau.mapper")
//MapperScan是mapper扫描器，这样的话springboot  就能够够根据mapper接口自动生成对应的实现类。
public class MyBatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开 启 count 的 join 优 化 , 只 针 对 部 分 left join
        paginationInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true)); return paginationInterceptor;
    }
}
