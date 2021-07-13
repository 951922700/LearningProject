package com.lyl.dao;

import com.lyl.entity.QueryVo;
import com.lyl.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
/**
 * user的Dao层
 */
public interface UserDao {
    /**
     * 查询所有user
     * @return
     */
    List<User> findAll();

    /**
     * 一对多查询，同时查询到user对应的多个account
     * @return
     */
    List<User> findAlls();

    /**
     *增加user
     * @param user
     */
    void addUser(User user);

    /**
     * 更新user表
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 通过id获取user
     * @param id
     * @return User
     */
    User findById(Integer id);

    /**
     * 模糊查询根据名字匹配返回所有模糊结果
     * @param userName
     * @return List<User>
     */
    List<User> findByName(String userName);

    /**
     * 查询用户总数
     * @return
     */
    int findTotal();

    /**
     * 根据queryVo中的条件查询用户
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);

    /**
     * 根据传入参数条件
     * @param user 查询的条件 有可能有用户名 有可能有性别 也有可能有地址 还有可能是都有
     * @return
     */
    List<User> findUserByCondition(User user);

    /**
     * 根据queryvo中提供的id集合，查询用户信息
     * @param vo
     * @return
     */
    List<User> findUserInIds(QueryVo vo);

    /**
     * 多对多测试
     * @return
     */
    List<User> findAllUserJoinRole();

    /**
     * 一对多延迟
     * @return
     */
    List<User> findAllYanchi();

    /**
     * 利用注解开发，查询语句实现
     * results标签解决列名不一致问题，并且id属性指定唯一标识,这个map的id不能与具体dao层配置的mapid一样，否则报错
     * result的前五行的配置就能够解决上面的问题了，第六行是一对多的配置
     * @return
     */
    @Select(value="select * from user")
    @Results(id = "userMapAnnotation",value = {
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "address",property = "address"),
            @Result(column = "birthday",property = "birthday"),
            @Result(column = "username",property = "userName"),
            @Result(column = "sex",property = "sex"),
            @Result(property = "accounts",column = "id",many = @Many(select = "com.lyl.dao.AccountDao.findAccountByUid",fetchType = FetchType.LAZY))
    })
    List<User> findAllAnotation();

    /**
     * 注解保存用户
     * @param user
     */
    @Insert("insert into user(username,address,sex,birthday)values(#{userName},#{address},#{sex},#{birthday})")
    @ResultMap(value = {"userMapAnnotation"})
    void saveUser(User user);

    /**
     * 注解updata
     * @param user
     */
    @Update("update user set username=#{userName},sex=#{sex},birthday=#{birthday},address=#{address} where id=#{id}")
    void updateUserAnnotation(User user);

    /**
     * 注解delete
     */
    @Delete("delete from user where id=#{id}")
    void deleteUserAnnotation(Integer userId);

}
