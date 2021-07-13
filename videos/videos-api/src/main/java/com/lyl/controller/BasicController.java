package com.lyl.controller;

import com.lyl.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BasicController {


    @Autowired
    public RedisOperator redis;

    public static final String USER_REDIS_SESSION="user-redis-session";

    // 文件保存的命名空间
    public static final String FILE_SPACE = "F:/videos_upload_file";

    // ffmpeg所在目录
    public static final String FFMPEG_EXE = "E:\\ffmpeg-4.3.1-win64-static\\bin\\ffmpeg.exe";

    // 每页分页的记录数
    public static final Integer PAGE_SIZE = 5;
}
