package com.lyl.dao.impl;

import com.lyl.dao.IAccountDao;
import com.lyl.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 当有很多的Dao的时候，jdbctemplate的创建就是重复代码这个时候继承JdbcDaoSupport可以不写下面注释的那段
 * extends JdbcDaoSupport
 *
 * super.getJdbcTemplate().
 */
@Repository("accountDao")
public class AccountImpl  implements IAccountDao {

   /* private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }*/
   @Autowired
   private JdbcTemplate jdbcTemplate;

    public Account findAccountById(Integer accountId) {
        //List<Account> accounts=jdbcTemplate.query("select * from account where id=?",new BeanPropertyRowMapper<Account>(Account.class),accountId);
        List<Account> accounts=jdbcTemplate.query("select * from account where id=?",new BeanPropertyRowMapper<Account>(Account.class),accountId);
        return accounts.isEmpty()?null:accounts.get(0);
    }

    public Account findAccountByName(String accountName) {
        List<Account>accounts=jdbcTemplate.query("select * from account where name=?",new BeanPropertyRowMapper<Account>(Account.class),accountName);
        return accounts.isEmpty()?null:accounts.get(0);
    }

    public void updateAccount(Account account) {
        jdbcTemplate.update("update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());

    }
}
