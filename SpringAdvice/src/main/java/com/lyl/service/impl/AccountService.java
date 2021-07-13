package com.lyl.service.impl;

import com.lyl.dao.IAccountDao;
import com.lyl.domain.Account;
import com.lyl.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Transactional里面有事务属性的一些内容可以自己设置
 */
@Service("accountService")
@Transactional
public class AccountService implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    public void transfer(String sourceName, String targetName, Float money) {
        Account source=accountDao.findAccountByName(sourceName);//获取一个conn连接
        Account target=accountDao.findAccountByName(targetName);//获取一个
        source.setMoney(source.getMoney()-money);
        target.setMoney(target.getMoney()+money);
        accountDao.updateAccount(source);//获取一个
        int i=1/0;
        accountDao.updateAccount(target);//获取一个
    }
}
