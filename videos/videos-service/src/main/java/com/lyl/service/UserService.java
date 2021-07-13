package com.lyl.service;


import com.lyl.pojo.Users;
import com.lyl.pojo.UsersReport;

public interface UserService {
    /**
     * 判断用户是否存在
     * @param username
     * @return
     */
    public boolean queryUsernameIsExist(String username);


    /**
     * 保存用户
     * @param users
     */
    public void saveUser(Users users);

    /**
     * 用户登录 根据用户名和密码查询用户
     * @param username
     * @param password
     */
    public Users queryUserForLogin(String username, String password);

    /**
     * 用户修改信息
     */
    public void updateUserInfo(Users users);

    /**
     * 查询用户信息
     */
    public Users queryUserInfo(String userId);

    /**
     * 判断是否有关联关系
     * @param userId
     * @param videoId
     * @return
     */
    public boolean isUserLikeVideo(String userId,String videoId);

    /**
     * @Description: 增加用户和粉丝的关系
     */
    public void saveUserFanRelation(String userId, String fanId);

    /**
     * @Description: 删除用户和粉丝的关系
     */
    public void deleteUserFanRelation(String userId, String fanId);

    /**
     * @Description: 查询用户是否关注
     */
    public boolean queryIfFollow(String userId, String fanId);

    /**
     * @Description: 举报用户
     */
    public void reportUser(UsersReport userReport);
}
