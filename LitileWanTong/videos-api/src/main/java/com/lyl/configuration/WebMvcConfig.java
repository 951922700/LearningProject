package com.lyl.configuration;

import com.lyl.controller.interceptor.MiniInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * 配置静态资源路径
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //这个时候还需要加 classpath:/META-INF/resources/ 才能访问swagger
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("file:/tmp/wantong/");
               // .addResourceLocations("file:F:/videos_upload_file/")

    }

    /**
     * 添加拦截器
     */
    @Bean
    public MiniInterceptor miniInterceptor(){
        return new MiniInterceptor();
    }

 /*   @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截路径
        registry.addInterceptor(miniInterceptor()).addPathPatterns("/user/**")
                                                  .addPathPatterns("/bgm/**")
                                                  .addPathPatterns("/video/upload","/video/uploadCover"
                                                  ,"/video/userLike","/video/userUnlike")
                                                  .excludePathPatterns("/user/queryPublisher");
    }*/
}
