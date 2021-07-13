package com.lyl.controller;

import com.lyl.pojo.Users;
import com.lyl.pojo.vo.UsersVo;
import com.lyl.service.UserService;
import com.lyl.utils.IMoocJSONResult;
import com.lyl.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 用Api注解来使用swagger2生成接口文档
 */
@RestController
@Api(value = "用户注册登录接口",tags = {"注册和登录controller"})
public class RegistLoginController extends BasicController{

    @Autowired
    private UserService userService;

    /**
     * 只不过RequestBody 接收的是请求体里面的数据；而RequestParam接收的是key-value里面的参数
     * 注册接口
     *
     * @ApiOperation(value = "用户注册",notes = "用户注册接口")
     * 除了要加这个注解还要去实体类加注解
     * @return
     */
    @PostMapping("/regist")
    @ApiOperation(value = "用户注册",notes = "用户注册接口")
    public IMoocJSONResult regist(@RequestBody Users users) throws Exception{
        //1.判断用户名和密码是否为空  这个方法判断null 和""
        if(StringUtils.isEmpty(users.getUsername())||StringUtils.isEmpty(users.getPassword())){
            return IMoocJSONResult.errorMsg("用户名和密码不能为空");
        }
        //2.判断用户名是否存在
        boolean bool=userService.queryUsernameIsExist(users.getUsername());

        //3.保存用户注册信息
        if(!bool){
            //用户名不存在可以注册
            users.setNickname(users.getUsername());
            users.setPassword(MD5Utils.getMD5Str(users.getPassword()));
            users.setFansCounts(0);
            users.setReceiveLikeCounts(0);
            users.setFollowCounts(0);
            //System.out.println("controller:我运行了吗");
            userService.saveUser(users);
        }else{
            return IMoocJSONResult.errorMsg("用户名已存在，请换一个试试");
        }
        //redis保存用户session
        String uniqueToken= UUID.randomUUID().toString();
        redis.set(USER_REDIS_SESSION+":"+users.getId(),uniqueToken,1000*60*30);
        UsersVo usersVo=new UsersVo();
        BeanUtils.copyProperties(users,usersVo);//将users中对应同字段的属性复制到usersVo中
        usersVo.setUserToken(uniqueToken);


        return IMoocJSONResult.ok(usersVo);
    }

    /**
     * 传入user对象创建redissession
     * @param userModel
     * @return
     */
    public UsersVo setUserRedisSessionToken(Users userModel) {
        String uniqueToken = UUID.randomUUID().toString();
        redis.set(USER_REDIS_SESSION + ":" + userModel.getId(), uniqueToken, 1000 * 60 * 30);

        UsersVo userVO = new UsersVo();
        BeanUtils.copyProperties(userModel, userVO);
        userVO.setUserToken(uniqueToken);
        return userVO;

    }


    @ApiOperation(value="用户登录", notes="用户登录的接口")
    @PostMapping("/login")
    public IMoocJSONResult login(@RequestBody Users user) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();

//		Thread.sleep(3000);

        // 1. 判断用户名和密码必须不为空
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return IMoocJSONResult.ok("用户名或密码不能为空...");
        }

        // 2. 判断用户是否存在  这里要解密
        Users userResult = userService.queryUserForLogin(username,
                MD5Utils.getMD5Str(user.getPassword()));

        // 3. 返回
        if (userResult != null) {
            userResult.setPassword("");
            UsersVo userVO = setUserRedisSessionToken(userResult);
            return IMoocJSONResult.ok(userVO);
        } else {
            return IMoocJSONResult.errorMsg("用户名或密码不正确, 请重试...");
        }
    }


    /**
     * 通过删除session来进行注销
     * @param userId
     * @return
     * @throws Exception
     */
    @ApiOperation(value="用户注销", notes="用户注销的接口")
    @ApiImplicitParam(name="userId", value="用户id", required=true,
            dataType="String", paramType="query")//给特定参数配置
    @PostMapping("/logout")
    public IMoocJSONResult logout(String userId) throws Exception {
        redis.del(USER_REDIS_SESSION + ":" + userId);
        return IMoocJSONResult.ok();
    }
}
