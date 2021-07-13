package com.lyl.test;


import com.lyl.dao.UserDao;
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

public class MybatisTest {
    private Reader reader;
    private SqlSession sqlSession;
    private UserDao userDao;

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
         userDao=sqlSession.getMapper(UserDao.class);
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
    public void  testFindAll(){
        //5.使用代理对象执行方法
        List<User> userList=userDao.findAll();
        for(User user:userList){
            System.out.println(user);
        }
    }
    @Test
    public void  testFindAllUserRole(){
        //5.使用代理对象执行方法
        List<User> userList=userDao.findAllUserJoinRole();
        for(User user:userList){
            System.out.println("----每个用户的信息----");
            System.out.println(user);
            System.out.println(user.getRoles());
        }
    }
    @Test
    public void  testFindAllS(){
        //5.使用代理对象执行方法
        List<User> userList=userDao.findAlls();
        for(User user:userList){
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }
    @Test
    public void testAdd() {
        User user=new User();
        user.setUserName("邓丁");
        user.setAddress("广东湛江");
        user.setBirthday(new Date());
        user.setSex("女");
        System.out.println("保存之前"+user);
        //执行insert方法
        userDao.addUser(user);
        System.out.println("保存之后"+user);
    }
    @Test
    public void testUpdate() {
        User user=new User();
        user.setId(3);
        user.setUserName("邓丁");
        user.setAddress("广东湛江");
        user.setBirthday(new Date());
        user.setSex("狗");
        //执行insert方法
        userDao.updateUser(user);
    }
    @Test
    public void testDelete() {
        userDao.deleteUser(2);
    }
    @Test
    public void testFindById() {
        /**
         * 测试查询一个
         */
        User user=userDao.findById(1);
        System.out.println(user);
    }

    @Test
    public void testFindByName() {
        /**
         * 模糊查询测试，需要在输入的时候给%
         */
        List<User> users=userDao.findByName("%丁%");
        for(User user:users)
            System.out.println(user);
    }
    @Test
    public void testFindTotal(){
        int count=userDao.findTotal();
        System.out.println(count);
    }
    @Test
    public void testFindByVo() {
        /**
         * 模糊查询测试，需要在输入的时候给%
         * 思考：为什么不直接用user作为参数呢？
         * 因为只是这个例子没有体现出这个的方便性
         * 如果我的模糊查询需要n个条件，那这个接口传值的时候在UserDao配置文件就要写很多歌parameterType
         * 把这个类封装成一个对象就能够更加方便快捷
         */
        QueryVo vo=new QueryVo();
        User user=new User();
        user.setUserName("%丁%");
        vo.setUser(user);
        List<User> users=userDao.findUserByVo(vo);
        for(User u:users)
            System.out.println(u);
    }
    @Test
    public void findByCondition(){
        User user=new User();
        user.setUserName("邓丁");
        List<User> users=userDao.findUserByCondition(user);
        for(User u:users)
        {
            System.out.println(u);
        }
    }

    @Test
    public void findUserInIds(){
        QueryVo vo=new QueryVo();
        List<Integer> list=new ArrayList<Integer>();
        list.add(1);
        list.add(4);
        vo.setIds(list);

        List<User> users=userDao.findUserInIds(vo);
        for(User user:users){
            System.out.println(user);
        }
    }
    @Test
    public void  testFindAllYanchi(){
        //5.使用代理对象执行方法
        List<User> userList=userDao.findAllYanchi();
        for(User user:userList){
            System.out.println(user);
        }
    }
    @Test
    public void testFindAllAnotstion(){
        List<User> users=userDao.findAllAnotation();
        for(User user:users){
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    /**
     * 测试注解insert
     */
    @Test
    public void testSaveUser(){
        User user=new User();
        user.setUserName("妈妈");
        user.setSex("女");
        userDao.saveUser(user);
    }

    @Test
    public void testUpdateUser(){
        User user=new User();
        user.setUserName("爸爸");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("广州市番禺区");
        user.setId(6);
        userDao.updateUserAnnotation(user);
    }

    /**
     * 测试注解delete
     */
    @Test
    public void testDeleteUser(){
        userDao.deleteUserAnnotation(4);
    }
}
