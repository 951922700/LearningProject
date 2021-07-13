package com.lyl.mapper;

import com.lyl.pojo.Users;
import com.lyl.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
public interface UsersMapper extends MyMapper<Users> {

    /**
     * 喜欢数量累加
     * @param userId
     */
    public void addReceiveLikeCount(String userId);


    /**
     * 喜欢数量累加
     * @param userId
     */
    public void reduceReceiveLikeCount(String userId);

    /**
     * 增加粉丝数量
     * @param userId
     */
    public void addFansCount(String userId);

    /**
     * 增加关注者数量
     */
    public void addFollowerCount(String userId);

    /**
     * 减少粉丝数量
     */
    public void reduceFansCount(String userId);

    /**
     * 减少关注者数量
     */
    public void reduceFollowerCount(String userId);
}