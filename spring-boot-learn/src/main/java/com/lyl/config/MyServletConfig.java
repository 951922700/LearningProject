package com.lyl.config;

import com.lyl.Filter.MyFilter;
import com.lyl.Listener.MyListener;
import com.lyl.Servlet.MyServlet;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class MyServletConfig {
    /**
     * 内置servlet容器定制器
     */
    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer()
    {
        return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>()
        {

            @Override
            public void customize(ConfigurableServletWebServerFactory factory) {
                factory.setPort(80);
            }
        };
    }

    //注册三大组件
    @Bean
    public ServletRegistrationBean myServlet()
    {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
        return servletRegistrationBean;
    }

    //过滤器，能够对请求进行过滤处理和拦截器有点像
    @Bean
    public FilterRegistrationBean myFilter()
    {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return filterRegistrationBean;
    }
    //servlet产生销毁监听器   / 拦截所有请求 静态资源 但不拦截jsp  /*拦截jsp
    @Bean
    public ServletListenerRegistrationBean myListener()
    {
        ServletListenerRegistrationBean<MyListener> myListenerServletListenerRegistrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return myListenerServletListenerRegistrationBean;
    }
}
