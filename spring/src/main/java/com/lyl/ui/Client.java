package com.lyl.ui;

import com.lyl.dao.AccountDao;
import com.lyl.entity.bean;
import com.lyl.service.AccountService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {
    public static void main(String[] args) {
        //ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        AccountService as = (AccountService) ac.getBean("accountService");
        //AccoutnService as2 = (AccoutnService) ac.getBean("accountService");
       // as.saveAccount();
        // System.out.println(as==as2);
        //AccountDao ad = (AccountDao) ac.getBean("accountDao");
        //System.out.println(ad);
        ac.close();

    }
}
