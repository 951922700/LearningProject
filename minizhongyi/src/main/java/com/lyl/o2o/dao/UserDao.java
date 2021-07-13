package com.lyl.o2o.dao;

import com.lyl.o2o.entity.User;

public interface UserDao {
    /**
     * 创建用户
     * @param user
     */
    void createUser(User user);

    /**
     * 通过account和密码查询用户信息
     * @param user
     * @return
     */
    User selectByAccount(User user);

    /**
     * 通过account查询
     */
    User selectAccount(User user);

    /**
     * 更新问题收藏
     * @param user
     */
    void updateQuestionCollect(User user);

    /**
     * 更新学习内容收藏
     * @param user
     */
    void updateUnitCollect(User user);

    /**
     * 更新密码
     */
    void updatePassword(User user);
}
