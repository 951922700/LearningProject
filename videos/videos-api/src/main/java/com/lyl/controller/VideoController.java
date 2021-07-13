package com.lyl.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import com.lyl.pojo.Users;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lyl.enums.VideoStatusEnum;
import com.lyl.pojo.Bgm;
import com.lyl.pojo.Comments;
import com.lyl.pojo.Videos;
import com.lyl.service.BgmService;
import com.lyl.service.VideoService;
import com.lyl.utils.FetchVideoCover;
import com.lyl.utils.IMoocJSONResult;
import com.lyl.utils.MergeVideoMp3;
import com.lyl.utils.PagedResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@Api(value="视频相关业务的接口", tags= {"视频相关业务的controller"})
@RequestMapping("/video")
public class VideoController extends BasicController {

    @Autowired
    private BgmService bgmService;

    @Autowired
    private VideoService videoService;


    @ApiOperation(value="上传视频", notes="上传视频的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId", value="用户id", required=true,
                    dataType="String", paramType="form"),
            @ApiImplicitParam(name="bgmId", value="背景音乐id", required=false,
                    dataType="String", paramType="form"),
            @ApiImplicitParam(name="videoSeconds", value="背景音乐播放长度", required=true,
                    dataType="String", paramType="form"),
            @ApiImplicitParam(name="videoWidth", value="视频宽度", required=true,
                    dataType="String", paramType="form"),
            @ApiImplicitParam(name="videoHeight", value="视频高度", required=true,
                    dataType="String", paramType="form"),
            @ApiImplicitParam(name="desc", value="视频描述", required=false,
                    dataType="String", paramType="form")
    })
    @PostMapping(value="/upload", headers="content-type=multipart/form-data")
    public IMoocJSONResult upload(String userId,
                                  String bgmId, double videoSeconds,
                                  int videoWidth, int videoHeight,
                                  String desc,
                                  @ApiParam(value="短视频", required=true)
                                          MultipartFile file) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return IMoocJSONResult.errorMsg("用户id不能为空...");
        }

        // 文件保存的命名空间
		//String fileSpace = "F:/videos_upload_file";
        // 保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/video";
        String coverPathDB = "/" + userId + "/video";

        //最终放在video文件夹的视频和封面的文件名字
        //文件名
        String videoOutputName = UUID.randomUUID().toString();

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        // 文件上传的最终保存路径
        String finalVideoPath = "";
        //用来保存传过来的文件名字
        String tempFileName="";
        try {
            if (file != null) {

                String fileName = file.getOriginalFilename();
                tempFileName=fileName;
                // abc.mp4
                /*String arrayFilenameItem[] =  fileName.split("\\.");//.要转义  将传来的文件名弄成图片的名字
                String fileNamePrefix = "";
                for (int i = 0 ; i < arrayFilenameItem.length-1 ; i ++) {
                    fileNamePrefix += arrayFilenameItem[i];
                }*/
                // fix bug: 解决小程序端OK，PC端不OK的bug，原因：PC端和小程序端对临时视频的命名不同
				//String fileNamePrefix = fileName.split("\\.")[0];

                if (StringUtils.isNotBlank(fileName)) {
                    //后面 不进行bgm合成操作文件名字就是wx给的
                    finalVideoPath = FILE_SPACE + uploadPathDB + "/" + fileName;
                    //finalVideoPath = fileSpace + uploadPathDB + "/" + fileName;
                    // 设置数据库保存的路径  相对路径
                    uploadPathDB += ("/" + fileName);
                    coverPathDB = coverPathDB + "/" + videoOutputName + ".jpg";

                    File outFile = new File(finalVideoPath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        // 创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }

                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
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

        // 判断bgmId是否为空，如果不为空，  为空就不用那个ffmpeg工具了
        // 那就查询bgm的信息，并且合并视频，生产新的视频
        if (StringUtils.isNotBlank(bgmId)) {
            Bgm bgm = bgmService.queryBgmById(bgmId);
            String mp3InputPath = FILE_SPACE + bgm.getPath();

            MergeVideoMp3 tool = new MergeVideoMp3(FFMPEG_EXE);
            String videoInputPath = finalVideoPath;

            //临时无声文件路径
            String tempPath=FILE_SPACE+"/"+userId+"/video/temp.mp4";

            //文件名
            videoOutputName += ".mp4";

            //最终路径
            uploadPathDB = "/" + userId + "/video" + "/" + videoOutputName;
            finalVideoPath = FILE_SPACE + uploadPathDB;


            tool.convertorFirst(videoInputPath,tempPath);//生成无声临时文件
            tool.convertor(tempPath, mp3InputPath, videoSeconds, finalVideoPath);//无声和bgm合并生成最终文件
        }else{
            //此时没有进行bgm操作  修改封面名字为传过来文件名
            String arrayFilenameItem[] =  tempFileName.split("\\.");
            String fileNamePrefix = tempFileName.split("\\.")[0];
            coverPathDB = "/" + userId + "/video/"+fileNamePrefix+".jpg";
        }
        System.out.println("uploadPathDB=" + uploadPathDB);
        System.out.println("finalVideoPath=" + finalVideoPath);

        // 对视频进行截图
        FetchVideoCover videoInfo = new FetchVideoCover(FFMPEG_EXE);
        videoInfo.getCover(finalVideoPath, FILE_SPACE + coverPathDB);

        System.out.println("filepath:\n"+finalVideoPath+"\n"+FILE_SPACE + coverPathDB);
        // 保存视频信息到数据库
        Videos video = new Videos();
        video.setAudioId(bgmId);
        video.setUserId(userId);
        video.setVideoSeconds((float)videoSeconds);
        video.setVideoHeight(videoHeight);
        video.setVideoWidth(videoWidth);
        video.setVideoDesc(desc);
        video.setVideoPath(uploadPathDB);
        video.setCoverPath(coverPathDB);
        video.setStatus(VideoStatusEnum.SUCCESS.value);
        video.setCreateTime(new Date());

      String videoId = videoService.saveVideo(video);


        return IMoocJSONResult.ok(videoId);
    }

    @Deprecated
    @ApiOperation(value="上传封面", notes="上传封面的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId", value="用户id", required=true,
                    dataType="String", paramType="form"),
            @ApiImplicitParam(name="videoId", value="视频主键id", required=true,
                    dataType="String", paramType="form")
    })
    @PostMapping(value="/uploadCover", headers="content-type=multipart/form-data")
    public IMoocJSONResult uploadCover(String userId,
                                       String videoId,
                                       @ApiParam(value="视频封面", required=true)
                                               MultipartFile file) throws Exception {

        if (StringUtils.isBlank(videoId) || StringUtils.isBlank(userId)) {
            return IMoocJSONResult.errorMsg("视频主键id和用户id不能为空...");
        }

        // 文件保存的命名空间
//		String fileSpace = "C:/imooc_videos_dev";
        // 保存到数据库中的相对路径
        String uploadPathDB = "/" + userId + "/video";

        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        // 文件上传的最终保存路径
        String finalCoverPath = "";
        try {
            if (file != null) {

                String fileName = file.getOriginalFilename();
                if (StringUtils.isNotBlank(fileName)) {

                    finalCoverPath = FILE_SPACE + uploadPathDB + "/" + fileName;
                    // 设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);

                    File outFile = new File(finalCoverPath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        // 创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }

                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = file.getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
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

        videoService.updateVideo(videoId, uploadPathDB);

        return IMoocJSONResult.ok();
    }

    /**
     * @Description: 分页和搜索查询视频列表
     * isSaveRecord：1 - 需要保存
     * 				 0 - 不需要保存 ，或者为空的时候
     **/
    @ApiOperation(value="分页和搜索查询视频列表", notes="分页和搜索查询视频列表接口")
    @PostMapping(value="/showAll")
    public IMoocJSONResult showAll(@RequestBody Videos video, Integer isSaveRecord,
                                   Integer page, Integer pageSize) throws Exception {

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = PAGE_SIZE;
        }

        PagedResult result = videoService.getAllVideos(video, isSaveRecord, page, pageSize);
        return IMoocJSONResult.ok(result);
    }

    @ApiOperation(value="获取热搜词", notes="获取热搜词接口")
    @PostMapping(value="/hot")
    public IMoocJSONResult hot() throws Exception {
        return IMoocJSONResult.ok(videoService.getHotwords());
    }

    @PostMapping(value="/userLike")
    public IMoocJSONResult userLike(String userId, String videoId, String videoCreaterId)
            throws Exception {
        videoService.userLikeVideo(userId, videoId, videoCreaterId);
        return IMoocJSONResult.ok();
    }

    @PostMapping(value="/userUnLike")
    public IMoocJSONResult userUnLike(String userId, String videoId, String videoCreaterId) throws Exception {
        videoService.userUnLikeVideo(userId, videoId, videoCreaterId);
        return IMoocJSONResult.ok();
    }

    /**
     *   @Description: 我收藏(点赞)过的视频列表
     */


    @PostMapping("/showMyLike")
    public IMoocJSONResult showMyLike(String userId, Integer page, Integer pageSize) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return IMoocJSONResult.ok();
        }

        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 6;
        }

        PagedResult videosList = videoService.queryMyLikeVideos(userId, page, pageSize);

        return IMoocJSONResult.ok(videosList);
    }


    /**
     *  @Description: 我关注的人发的视频
     */

    @PostMapping("/showMyFollow")
    public IMoocJSONResult showMyFollow(String userId, Integer page) throws Exception {

        if (StringUtils.isBlank(userId)) {
            return IMoocJSONResult.ok();
        }

        if (page == null) {
            page = 1;
        }

        int pageSize = 6;

        PagedResult videosList = videoService.queryMyFollowVideos(userId, page, pageSize);

        return IMoocJSONResult.ok(videosList);
    }

    /**
     * 添加评论
     * @param comment
     * @param fatherCommentId
     * @param toUserId
     * @return
     * @throws Exception
     */
    @PostMapping("/saveComment")
    public IMoocJSONResult saveComment(@RequestBody Comments comment,
                                       String fatherCommentId, String toUserId) throws Exception {

        comment.setFatherCommentId(fatherCommentId);
        comment.setToUserId(toUserId);
        videoService.saveComment(comment);
        return IMoocJSONResult.ok();
    }

    /**
     * 查询评论
     * @param videoId
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    @PostMapping("/getVideoComments")
    public IMoocJSONResult getVideoComments(String videoId, Integer page, Integer pageSize) throws Exception {

        if (StringUtils.isBlank(videoId)) {
            return IMoocJSONResult.errorMsg("videoId为空");
        }

        // 分页查询视频列表，时间顺序倒序排序
        if (page == null) {
            page = 1;
        }

        if (pageSize == null) {
            pageSize = 10;
        }

        PagedResult list = videoService.getAllComments(videoId, page, pageSize);

        return IMoocJSONResult.ok(list);
    }

}