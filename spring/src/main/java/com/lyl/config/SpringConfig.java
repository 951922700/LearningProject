package com.lyl.config;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

/*
 * 该类是一个配置类，用于与bean.xml一致
 * spring新注解
 * @ Configuration
 *  作用：表明当前类是一个配置类
 *  细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时，该注解可以不写
 * @ ComponentScan
 *  作用：用于通过注解指定spring在创建容器时要扫描的包等同于在xml配置了：<context:component-scan base-package="com.lyl"></context:component-scan>
 *  属性：
 *      value和basePackages一致
 *@ Bean
 *      作用：用于把当前方法的返回值作为bean对象存入spring的ioc容器中
 *      属性：name：用于指定bean的id。默认值为当前方法的名称
 *      细节：
 *      当时用注解配置方法时，如果方法有参数，spring框架会去容器查找对应bean对象查找方式与autowired的方式一致
 * @ Scope
 * @ Import(jdbcConfig.class) 用于导入其他配置类
 * @ PropertySource 导入参数文件
 *      属性：路径 前面要加classpath
 */
@Configuration
@ComponentScans(@ComponentScan(basePackages = {"com.lyl"}))
@PropertySource("classpath:jdbc.properties")
public class SpringConfig {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    /**
     * 用于创建一个QueryRunner对象
     * @param dataSource
     * @return
     */
    @Bean
    @Scope("singleton")
    public QueryRunner createQueryRuner(DataSource dataSource ){
        return new QueryRunner(dataSource);
    }

    /**
     * 创建数据源对象
     * @return
     */
    @Bean
    public DataSource createDateSource(){
        try{
        ComboPooledDataSource ds=new ComboPooledDataSource();
        ds.setDriverClass(driver);
        //这里的 &不用转义符号 在XML才要
        ds.setJdbcUrl(url);
        ds.setUser(username);
        ds.setPassword(password);
        return ds;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
