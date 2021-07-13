package com.lyl.o2o.service;


import com.lyl.o2o.entity.User;



public interface UserService {
    /**
     * 创建用户
     * @param user
     */
    void createUser(User user);

    /**
     * 通过account查询用户信息
     * @param user
     * @return
     */
    User selectByAccount(User user);

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

    User selectAccount(User user);

    void updatePassword(User user);
}
