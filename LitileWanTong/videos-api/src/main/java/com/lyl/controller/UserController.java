package com.lyl.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lyl.pojo.Babyinfo;
import com.lyl.pojo.User;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;




import com.lyl.service.UserService;
import com.lyl.utils.JSONResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="用户相关业务的接口", tags= {"用户相关业务的controller"})
@RequestMapping("/user")
public class UserController extends BasicController {

    @Autowired
    private UserService userService;

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
   /* @ApiOperation(value="用户上传头像", notes="用户上传头像的接口")
    @ApiImplicitParam(name="userId", value="用户id", required=true,
            dataType="String", paramType="query")
    @PostMapping("/uploadFace")
    public JSONResult uploadFace(String userId,
                                 @RequestParam("file") MultipartFile[] files) throws Exception {

        //StringUtils.isBlank(String str) 判断某字符串是否为空或长度为0或由空白符(whitespace) 构成
        //StringUtils.isEmpty(String str) 判断某字符串是否为空，为空的标准是 str==null 或 str.length()==0
        if (StringUtils.isBlank(userId)) {
            return JSONResult.errorMsg("用户id不能为空...");
        }


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
                return JSONResult.errorMsg("上传出错...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JSONResult.errorMsg("上传出错...");
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

        return JSONResult.ok(uploadPathDB);
    }
    */
    //----------------

    /**
     * 注册  医生先不管
     * @param user
     * @return
     */
    @ApiOperation(value="用户注册", notes="传uid、name、city即可，icon后台默认设置一张图片到时候，医生id先不管")
    @PostMapping("/regist")
    public JSONResult registUser(@RequestBody User user){
        if (user.getUid().isEmpty()){
            return JSONResult.errorMsg("uid为空");
        }
        //判断是否有重复
        List<User> users=userService.getAll();
        for (User u:users) {
            if (user.getUid().equals(u.getUid())) {
                //已经有该用户
                return JSONResult.errorMsg("该用户已存在");
            }
        }
        user.setIcon("https://ruxianai.picp.vip/image/avatar.png");
        userService.registUser(user);
        Map result=new HashMap();
        result.put("success",true);
        return JSONResult.ok(result);
    }

    /**
     * 登录，只需要传uid判断即可
     */
    @ApiOperation(value="用户登录", notes="用户注册的接口")
    @GetMapping("/login")
    public JSONResult login(@RequestParam String uid){

        User user=userService.query(uid);
        if (user==null){
            return JSONResult.errorMsg("该用户已存在");

        }else{
            return JSONResult.ok(user);
        }
    }

    /**
     * 插入宝宝信息
     */
    @ApiOperation(value="宝宝信息插入", notes="宝宝信息插入的接口")
    @PostMapping("/addbabyinfo")
    public JSONResult insertBabyinfo(@RequestBody Babyinfo babyinfo){

       if (babyinfo.getUid()==null)
           return JSONResult.errorMsg("uid为空");
       else{
           userService.addBaby(babyinfo);
           return JSONResult.ok();
       }
    }

    /**
     * 查询宝宝信息
     */
    @ApiOperation(value="查询宝宝信息", notes="宝宝信息查询的接口")
    @PostMapping("/querybabyinfo")
    public JSONResult queryBabyinfo(@RequestParam String uid){
        if (uid==null)
            return JSONResult.errorMsg("uid为空");
        else{
            return JSONResult.ok(userService.queryBaby(uid));
        }
    }
}