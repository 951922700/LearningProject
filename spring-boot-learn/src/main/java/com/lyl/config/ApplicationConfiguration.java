package com.lyl.config;

import com.lyl.component.LoginHandlerInterceptor;
import com.lyl.component.MyLocaleResolver;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;


/**
 * 指明当前类是一个配置类
 * @Bean 指定某个bean
 *
 * 想要扩展MVC的功能在有configuration的注解的类下继承WebMvcConfigurationSupport
 * 新版是实现webMvcConfigurer接口
 */
//@EnableWebMvc   完全接管mvc，也就是自动配置被抛弃
@Configuration
public class ApplicationConfiguration implements WebMvcConfigurer {
    //将方法的返回值添加到容器中，容器中这个组件默认id就是方法名
   /* @Bean
    public HelloController helloController(){
        return new HelloController();
    }*/


    /**
     * 配置视图控制器
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //浏览器发送/lyl请求会转到 success界面
        registry.addViewController("/lyl").setViewName("success");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    /**
     * 添加拦截器
     * @param registry
     */
   @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //静态资源在springboot不会被拦截
       /* registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
        .excludePathPatterns("/index.html","/","/user/login","/asserts/**","/webjars/**");*/
    }

    /**
     * 添加一个静态资源的映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }

    /**
     * 把区域信息解析器加入bean中，springboot的自动配置是，如果没有我们自动添加的resolver的bean才是用自动的
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
