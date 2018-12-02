package com.springboot.autoconfigure;

import org.springframework.context.annotation.Configuration;
import com.springboot.web.interceptor.InterceptorLogin;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * webmvc 额外的配置
 */
@Configuration
public class WebMvcSupportConfiguration extends WebMvcConfigurerAdapter {

    /**
     * 注册spring拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new InterceptorLogin())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html","/","/user/login");

    }
    /**
     * 注册 servlet
    @Bean
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }
    *//**
     * 注册filter
     *//*
    @Bean
    public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new MyFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return registrationBean;
    }

    *//**
     * 注册 Listener
     *//*
    @Bean
    public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }*/


}
