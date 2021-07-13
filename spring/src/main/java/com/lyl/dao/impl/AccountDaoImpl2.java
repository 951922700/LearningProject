package com.lyl.dao.impl;

import com.lyl.dao.AccountDao;
import com.lyl.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("accountDao2")
public class AccountDaoImpl2 /*implements AccountDao*/ {

    /*public void saveAccount(){
        System.out.println("保存了账户22");
    }
*/

    public List<Account> findAllAccount() {
        return null;
    }

    public Account findAccountById(Integer id) {
        return null;
    }

    public void saveAccount(Account account) {

    }

    public void updateAccount(Account account) {

    }

    public void deleteAccount(Integer id) {

    }
}
