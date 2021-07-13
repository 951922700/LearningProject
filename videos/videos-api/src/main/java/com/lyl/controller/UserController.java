package com.lyl.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.lyl.pojo.vo.PublisherVideo;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lyl.pojo.Users;
import com.lyl.pojo.UsersReport;
//import com.lyl.pojo.vo.PublisherVideo;
import com.lyl.pojo.vo.UsersVo;
import com.lyl.service.UserService;
import com.lyl.utils.IMoocJSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="用户相关业务的接口", tags= {"用户相关业务的controller"})
@RequestMapping("/user")
public class UserController extends BasicController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="用户上传头像", notes="用户上传头像的接口")
    @ApiImplicitParam(name="userId", value="用户id", required=true,
            dataType="String", paramType="query")
    @PostMapping("/uploadFace")
    public IMoocJSONResult uploadFace(String userId,
                                      @RequestParam("file") MultipartFile[] files) throws Exception {

        //StringUtils.isBlank(String str) 判断某字符串是否为空或长度为0或由空白符(whitespace) 构成
        //StringUtils.isEmpty(String str) 判断某字符串是否为空，为空的标准是 str==null 或 str.length()==0
        if (StringUtils.isBlank(userId)) {
            return IMoocJSONResult.errorMsg("用户id不能为空...");
        }
        /**
         * 上传文件流程
         * 1.创建 FileOutputStream fileOutputStream = null;
         *     InputStream inputStream = null;
         *     两个文件
         * 2.自定义文件保存的位置和相对路径
         * 3.getOriginalFilename可以获取文件名字
         * 4.拼接最终路径创建file  并判断创建目录
         * 5.fileOutputStream = new FileOutputStream(outFile);
         *   inputStream = files[0].getInputStream();
         *   再用IOUtils复制文件
         *
         *   String fileName="new .txt";
         * String dir="mydir1/mydir2";
         * File f=new File(dir,fileName);
         * 当我直接
         * f.mkdirs();后
         * new .txt也变成了目录
         * 而f.getParentFile().mkdirs();
         * 只有mydir1/mydir2的目录
         */

        // 文件保存的命名空间
        String fileSpace = "F:/videos_upload_file";
        // 保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/face";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            if (files != null && files.length > 0) {

                String fileName = files[0].getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {
                    // 文件上传的最终保存路径
                    String finalFacePath = fileSpace + uploadPathDB + "/" + fileName;
                    // 设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);

                    File outFile = new File(finalFacePath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        //getParentFile()返回bai此抽象路径名的父路径名的抽象路径名，如果此路径名没有指定父目录，则返回 null。
                        // 创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = files[0].getInputStream();

                    IOUtils.copy(inputStream, fileOutputStream);//IOUtiles工具类把inputstream  copy到fileoutputStream对应文件夹下
                }

            } else {
                return IMoocJSONResult.errorMsg("上传出错...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return IMoocJSONResult.errorMsg("上传出错...");
        } finally {
            if (fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
        //将相对路径传过去   只要是动了api之外的都要install
        Users user = new Users();
        user.setId(userId);
        user.setFaceImage(uploadPathDB);
        userService.updateUserInfo(user);

        return IMoocJSONResult.ok(uploadPathDB);
    }

    @ApiOperation(value="查询用户信息", notes="查询用户信息的接口")
    @ApiImplicitParam(name="userId", value="用户id", required=true,
            dataType="String", paramType="query")
    @PostMapping("/query")
    public IMoocJSONResult query(String userId, String fanId) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return IMoocJSONResult.errorMsg("用户id不能为空...");
        }

        Users userInfo = userService.queryUserInfo(userId);
        UsersVo userVo = new UsersVo();
        BeanUtils.copyProperties(userInfo, userVo);


       userVo.setFollow(userService.queryIfFollow(userId, fanId));

        return IMoocJSONResult.ok(userVo);
    }

    @PostMapping("/queryPublisher")
    public IMoocJSONResult queryPublisher(String loginUserId, String videoId,
                                          String publishUserId) throws Exception {

        if (StringUtils.isBlank(publishUserId)) {
            return IMoocJSONResult.errorMsg("");
        }

        // 1. 查询视频发布者的信息
        Users userInfo = userService.queryUserInfo(publishUserId);
        UsersVo publisher = new UsersVo();
        BeanUtils.copyProperties(userInfo, publisher);

        // 2. 查询当前登录者和视频的点赞关系
        boolean userLikeVideo = userService.isUserLikeVideo(loginUserId, videoId);

        PublisherVideo bean = new PublisherVideo();
        bean.setUsersVo(publisher);
        bean.setUserLikeVideo(userLikeVideo);

        return IMoocJSONResult.ok(bean);
    }

    @PostMapping("/beyourfans")
    public IMoocJSONResult beyourfans(String userId, String fanId) throws Exception {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(fanId)) {
            return IMoocJSONResult.errorMsg("");
        }

        userService.saveUserFanRelation(userId, fanId);

        return IMoocJSONResult.ok("关注成功...");
    }

    @PostMapping("/dontbeyourfans")
    public IMoocJSONResult dontbeyourfans(String userId, String fanId) throws Exception {

        if (StringUtils.isBlank(userId) || StringUtils.isBlank(fanId)) {
            return IMoocJSONResult.errorMsg("");
        }

        userService.deleteUserFanRelation(userId, fanId);

        return IMoocJSONResult.ok("取消关注成功...");
    }



    @PostMapping("/reportUser")
    public IMoocJSONResult reportUser(@RequestBody UsersReport usersReport) throws Exception {

        // 保存举报信息
        userService.reportUser(usersReport);

        return IMoocJSONResult.errorMsg("举报成功...有你平台变得更美好...");
    }

}