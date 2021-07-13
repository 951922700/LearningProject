package com.lyl.test;


import com.lyl.dao.RoleDao;
import com.lyl.entity.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import java.io.Reader;
import java.util.List;

public class RoleTest {
    private Reader reader;
    private SqlSession sqlSession;
    private RoleDao roleDao;

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
        roleDao=sqlSession.getMapper(RoleDao.class);
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
        List<Role> roles=roleDao.findAll();
        for (Role role:roles){
            System.out.println("----每个角色信息----");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }
}
