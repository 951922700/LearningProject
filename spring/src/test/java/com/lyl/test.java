package com.lyl;

import com.lyl.config.SpringConfig;
import com.lyl.entity.Account;
import com.lyl.service.AccountService;
import com.lyl.service.Impl.AccountServiceImpl;
import com.lyl.ui.learnImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * spring整合junit
 *  1.导入spring整合junit的jar
 *  <dependency>
 *             <groupId>org.springframework</groupId>
 *             <artifactId>spring-test</artifactId>
 *             <version>5.0.2.RELEASE</version>
 *  </dependency>
 *  2.使用junit提供的一个注解把原有的main方法替换了，替换成spring提供的，因为junit的test方法是不会自动注入的
 * @ Runwith
 *  3.告知spring的运行器，spring和ioc创建是基于xml还是基于注解
 * @ ContextConfiguration
 *      location:指定xml文件的位置，加上classpath关键字，表示在类路径下
 *      classes:指定注解类所在的位置
 *  当我们使用spring 5.x版本的时候要求junit的jar必须是4.12以上
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class test {
    /**节省这一段 不能使用实现类对proxy代理进行转换
     * ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
     *
     * AccountService as = (AccountService) ac.getBean("accountService");
     * @ Qualifier("proxyAccountService")代理service用这个
     */
    @Autowired
    @Qualifier("proxyAccountService")
    private AccountService as;
    /*@Before
    public void init(){
        ac=new ClassPathXmlApplicationContext("bean.xml");
        as =(AccountService) ac.getBean("accountService");
    }*/
    @Test
    public void findAll() {
        List<Account> accounts=as.findAllAccount();
        for (Account account:accounts)
            System.out.println(account);
    }
    @Test
    public void testUpdate() {

    }
    @Test
    public void testFindOne() {
        //ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        //注解应该使用AnnotationConfigApplicationContext这个实现类，传入配置类的字节码文件
        ApplicationContext ac=new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService as =(AccountService) ac.getBean("accountService");
        Account account=as.findAccountById(1);
        System.out.println(account);
    }
    @Test
    public void testSave() {

    }
    @Test
    public void findDelete() {

    }
    @Test
    public void transfer(){
        as.transfer("aaa","bbb",100f);
    }
    @Test
    public void testAop(){
        as.aop();
    }
}
