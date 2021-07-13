package com.lyl.dao;

import com.lyl.domain.Account;

/**
 * 账户的持久层接口
 */
public interface IAccountDao {

    Account findAccountById(Integer accountId);

    Account findAccountByName(String accountName);

    void updateAccount(Account account);
}
