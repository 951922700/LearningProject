package com.lyl.service;

import com.lyl.domain.Account;

import java.util.List;

public interface AccountService {

    public List<Account> findAll();

    public void saveAccount(Account account);
}
