package com.chzero.imooc.ms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author CHZERO
 * @version 1.0
 * @date 2018-06-13 12:19
 * @email 827348260@qq.com
 * @description
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Autowired
    private UserArgumentResolver userArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){
        resolvers.add(this.userArgumentResolver);
    }
}
