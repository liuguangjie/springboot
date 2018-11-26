package com.springboot.web.servlet;

import com.springboot.SpringbootBeautifulApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;


/**
 * 整合tomcat  war 包必须要的一个类
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootBeautifulApplication.class);
    }
}
