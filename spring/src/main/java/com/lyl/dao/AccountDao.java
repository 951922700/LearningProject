package com.lyl.dao;


import com.lyl.entity.Account;

import java.util.List;

public interface AccountDao {
     //查询所有账户
     List<Account> findAllAccount();
     //查询单个账户
     Account findAccountById(Integer id);
     //保存操作
     void saveAccount(Account account);
     //更新
     void updateAccount(Account account);
     //删除
     void deleteAccount(Integer id);

     /**
      *
      * @param accountName
      * @return 如果结果超过一个抛出异常
      */
     Account findAccountByName(String accountName);
}
