package com.lyl.service.impl;

import com.lyl.dao.AccountDao;
import com.lyl.domain.Account;
import com.lyl.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    /**
     * spring会把用mybatis的sessionfactory将代理对象直接注入
     */
    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        System.out.println("业务层：查询所有账户。。。");
        return accountDao.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        System.out.println("业务层：保存账户。。。");
        accountDao.saveAccount(account);
    }
}
