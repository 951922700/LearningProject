package com.lyl.work.service;

import com.lyl.work.entity.Clocked;

public interface ClockedService {

    /**
     *通过openid查找打卡记录
     */
    Clocked queryClockedByid(String Id);

    /**
     * 更新最后打卡时间
     */
    void updateClocked(String Id);

    /**
     * 新建用户
     * 出现异常
     */
    void insertNewClocked(String NewId);

    /**
     * 查询打卡天数
     */
    Integer queryTimes(String Id);


    /**
     * 新建用户
     */
    void insertNewOpenId(String Id);


}
