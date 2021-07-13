package com.lyl.test;


import com.lyl.dao.AccountDao;
import com.lyl.dao.UserDao;
import com.lyl.entity.Account;
import com.lyl.entity.AccountUser;
import com.lyl.entity.QueryVo;
import com.lyl.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountTest {
    private Reader reader;
    private SqlSession sqlSession;
    private AccountDao accountDao;

    /**
     * 定义初始化操作
     */
    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件
        reader= Resources.getResourceAsReader("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory=new SqlSessionFactoryBuilder().build(reader);
        //3.使用工厂生产SqlSession对象
        sqlSession= sqlSessionFactory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        accountDao=sqlSession.getMapper(AccountDao.class);
    }

    /**
     * 释放资源
     */
    @After//用于测试方法执行之后执行
    public void destroy() throws Exception{
        //需要提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        reader.close();
    }
    @Test
    public void testFindAll(){
        List<Account> accounts=accountDao.findAll();
        for(Account account:accounts){
            System.out.println(account);
            System.out.println(account.getUser());
            System.out.println("----------------");
        }
    }

    /**
     * 多表查询
     */
    @Test
    public void testFindAllAccount(){
        List<AccountUser> aus=accountDao.findAllAccount();
        for(Account au:aus){
            System.out.println(au);
        }
    }
    /**
     * 延迟加载
     */
    @Test
    public void testFindAllAccountYanchi(){
        List<Account> aus=accountDao.findAllyanchi();
        /*for(Account au:aus){
            System.out.println(au);
            System.out.println(au.getUser());
        }*/
    }

    /**
     * 注解实现的一对一
     */
    @Test
    public void testFindAllAnnotation(){
        List<Account> accounts=accountDao.findAllAccountAnnotation();
        for(Account account:accounts){
            System.out.println("--每个账户信息--");
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }

}
