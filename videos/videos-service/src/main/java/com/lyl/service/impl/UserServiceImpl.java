package com.lyl.service.impl;

import com.lyl.mapper.UsersFansMapper;
import com.lyl.mapper.UsersLikeVideosMapper;
import com.lyl.mapper.UsersMapper;
import com.lyl.mapper.UsersReportMapper;
import com.lyl.pojo.Users;
import com.lyl.pojo.UsersFans;
import com.lyl.pojo.UsersLikeVideos;
import com.lyl.pojo.UsersReport;
import com.lyl.service.UserService;
import com.lyl.utils.IMoocJSONResult;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersFansMapper usersFansMapper;

    @Autowired
    private UsersLikeVideosMapper usersLikeVideosMapper;

    @Autowired
    private UsersReportMapper usersReportMapper;

    //自动生成id的工具类
    @Autowired
    private Sid sid;

    /**
     *SUPPORTS（支持）(TransactionDefinition.PROPAGATION_SUPPORTS) 如果当前有事务，则使用事务，如果没有，则不使用。
     * 例如：领导没饭吃，我也没饭吃，领导有饭吃，我也有饭吃，也就是说如下面的代码，我在savechild（）方法上加了事务，
     * 但是在父级方法中没有加，则不会有事务，如果我在父级方法中也加了事务，才会实现事务。
     * @param username
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        Users users=new Users();
        users.setUsername(username);
        //mybatis逆向工程生成的mapper里面有很多方法
        Users one = usersMapper.selectOne(users);
       // System.out.println("service:"+one);
        //one为空返回false
        return one==null ? false : true;
    }

    /**
     * REQUIRED（必须的）(TransactionDefinition.PROPAGATION_REQUIRED) 使用当前的事务，
     * 如果当前没有事务，则自己新建一个事务，子方法是必须运行在一个事务中的，如果当前存在事务，则加入这个事务，成为一个整体。
     * 例如：领导没有钱，但是我有钱，我就会自己买了吃（不存在事务）； 如果领导有钱，就会分一些给我（事务）
     * @param users
     */
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveUser(Users users) {
        String userID=sid.nextShort();
        users.setId(userID);
        usersMapper.insert(users);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserForLogin(String username, String password) {

        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("username", username);
        criteria.andEqualTo("password", password);
        //Example里面放了两个参数  下面mapper那个方法就是 里面有什么值就根据哪两个值查询
        Users result = usersMapper.selectOneByExample(userExample);

        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateUserInfo(Users users) {
        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("id",users.getId());

        usersMapper.updateByExampleSelective(users,userExample);//这个方法是哪个有值更新哪个  条件是criteria设置的值
        //usersMapper.updateByExample();这个是更新全部无论null
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Users queryUserInfo(String userId) {
        Example userExample = new Example(Users.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("id",userId);
        Users users = usersMapper.selectOneByExample(userExample);
        return users;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean isUserLikeVideo(String userId, String videoId) {
        if (StringUtils.isBlank(userId)||StringUtils.isBlank(videoId)) {
            return false;
        }

        Example userExample = new Example(UsersLikeVideos.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("videoId",videoId);
        UsersLikeVideos usersLikeVideos = usersLikeVideosMapper.selectOneByExample(userExample);
        if(usersLikeVideos!=null) return true;
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveUserFanRelation(String userId, String fanId) {
        UsersFans usersFans=new UsersFans();
        usersFans.setId(sid.nextShort());
        usersFans.setUserId(userId);
        usersFans.setFanId(fanId);
        usersFansMapper.insert(usersFans);
        usersMapper.addFansCount(userId);
        usersMapper.addFollowerCount(fanId);

    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteUserFanRelation(String userId, String fanId) {
        Example userExample = new Example(UsersFans.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("fanId",fanId);
        usersFansMapper.deleteByExample(userExample);
        usersMapper.reduceFansCount(userId);
        usersMapper.reduceFollowerCount(fanId);
    }

    @Override
    public boolean queryIfFollow(String userId, String fanId) {
        Example userExample = new Example(UsersFans.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("fanId",fanId);
        UsersFans usersFans = usersFansMapper.selectOneByExample(userExample);
        if(usersFans!=null) return true;
        return false;
    }

    @Override
    public void reportUser(UsersReport userReport) {
        String id=sid.nextShort();
        userReport.setId(id);
        userReport.setCreateDate(new Date());
        usersReportMapper.insert(userReport);
    }
}
