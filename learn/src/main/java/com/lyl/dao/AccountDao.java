package com.lyl.dao;

import com.lyl.entity.Account;
import com.lyl.entity.AccountUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * CacheNamespace注解开启二级缓存
 */
@CacheNamespace(blocking = true)
public interface AccountDao {
    /**
     * 查询所有账户，同时还要获取当前账户的所属用户信息
     * @return
     */
    List<Account> findAll();

    /**
     * 利用account子类来实现一对一的数据查询
     * @return
     */
    List<AccountUser> findAllAccount();

    /**
     * 延迟加载
     * @return
     */
    List<Account> findAllyanchi();

    /**
     * 根据用户id查询账户
     * @return
     */
    List<Account> findAccountByUid();

    /**
     * 一对一
     * 注解查询所有account并返回所属user
     * 给user配的时候column对应的是select语句里面要用的列
     * @return
     */
    @Select("select * from account")
    @Results(id = "accountMap",value = {
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "uid",property = "uid"),
            @Result(column = "money",property = "money"),
            @Result(property = "user",column = "uid",one = @One(select = "com.lyl.dao.UserDao.findById",fetchType = FetchType.EAGER))
    })
    List<Account> findAllAccountAnnotation();
}
