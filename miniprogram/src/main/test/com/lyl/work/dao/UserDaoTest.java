package com.lyl.work.dao;

import com.lyl.work.BaseTest;
import com.lyl.work.entity.SharingCase;
import com.lyl.work.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class UserDaoTest extends BaseTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private SharingCaseDao sharingCaseDao;
    @Test
    public void testqueryAll(){
        List<User> userList=userDao.queryUserAll();
        for (User user:userList){
            System.out.println(user);
        }
    }

    @Test
    public void testQueryOne(){
        //User user=userDao.queryUserById("1");
        //System.out.println(user);
        User user =new User();
        user.setUserName("Eublue");
        user.setUserImg("http");
        user.setOpenId("2");
        user.setCreateTime(new Date());
        userDao.insertUser(user);
    }
    @Test
    public void test(){
        SharingCase sharingCase=new SharingCase();
        sharingCase.setId("case1");
        SharingCase aCase = sharingCaseDao.queryById(sharingCase);
        System.out.println(aCase);
    }
}
