package com.lyl.o2o.dao;

import com.lyl.o2o.BaseTest;
import com.lyl.o2o.entity.Question;
import com.lyl.o2o.entity.Unit;
import com.lyl.o2o.entity.UnitTitle;
import com.lyl.o2o.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class DaoTest extends BaseTest {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UnitDao unitDao;

    @Autowired
    private QuestionDao questionDao;
    @Test
    public void testUser(){
       /* User user=new User();
        user.setAccount("951922700");
        user=userDao.selectByAccount(user);
        System.out.println(user);*/
       User user=new User();
       user.setAccount("999");
       user.setQuestionCollect("1");

       userDao.updateQuestionCollect(user);
    }
    @Test
    public void  testUnit(){
        Unit unit=new Unit();
        unit.setCid(1);
        unit = unitDao.queryUnitByCid(unit);
        System.out.println(unit);
        /*String content=unit.getContent();
        String[] strings = content.split("-->");
        for(String s:strings)
            System.out.println(s);*/
    }

    @Test
    public void testQuestion(){
        Question question=new Question();
        question.setCid(2);
        question=questionDao.queryByCid(question);
        System.out.println(question);
}
}
