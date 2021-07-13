package com.lyl.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lyl.mapper.*;
import com.lyl.pojo.Comments;
import com.lyl.pojo.SearchRecords;
import com.lyl.pojo.UsersLikeVideos;
import com.lyl.pojo.Videos;
import com.lyl.pojo.vo.CommentsVO;
import com.lyl.pojo.vo.VideosVO;
import com.lyl.service.VideoService;
import com.lyl.utils.PagedResult;
import com.lyl.utils.TimeAgoUtils;
import org.apache.ibatis.annotations.Param;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideosMapper videosMapper;

    @Autowired
    private VideosMapperCustom videosMapperCustom;

    @Autowired
    private SearchRecordsMapper searchRecordsMapper;

    @Autowired
    private UsersLikeVideosMapper usersLikeVideosMapper;

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private CommentsMapper commentsMapper;

    @Autowired
    private CommentsMapperCustom commentsMapperCustom;

    @Autowired
    private Sid sid;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String saveVideo(Videos video) {
        String id=sid.nextShort();
        video.setId(id);
        videosMapper.insertSelective(video);
        return id;
    }

    @Override
    public void updateVideo(String videoId, String coverPath) {
        Videos videos=new Videos();
        videos.setId(videoId);
        videos.setCoverPath(coverPath);
        videosMapper.updateByPrimaryKeySelective(videos);
    }
    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public PagedResult getAllVideos(Videos video, Integer isSaveRecord, Integer page, Integer pageSize) {
        //用video 里的desc接收搜索热词
        String desc=video.getVideoDesc();
        String userId=video.getUserId();
        //上下拉的时候传0，不是搜索查询
        if(isSaveRecord!=null&&isSaveRecord==1){
            SearchRecords searchRecords=new SearchRecords();
            searchRecords.setId(sid.nextShort());
            searchRecords.setContent(desc);
            searchRecordsMapper.insert(searchRecords);
        }


        PageHelper.startPage(page,pageSize);//调用这个方法后进行查询进行拦截和sql拼接limit
        List<VideosVO> list=videosMapperCustom.queryAllVideos(desc,userId);
        PageInfo<VideosVO> pageInfo=new PageInfo<>(list);

        PagedResult pagedResult=new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageInfo.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageInfo.getTotal());//总记录数
        return pagedResult;
    }



    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<String> getHotwords() {
        return searchRecordsMapper.getHotWords();
    }


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void userLikeVideo(String userId, String videoId, String videoCreaterId) {
        //1.保存视频喜欢和用户的关联关系
        String likeId=sid.nextShort();
        UsersLikeVideos usersLikeVideos=new UsersLikeVideos();
        usersLikeVideos.setId(likeId);
        usersLikeVideos.setUserId(userId);
        usersLikeVideos.setVideoId(videoId);
        usersLikeVideosMapper.insert(usersLikeVideos);

        //2.视频喜欢数量增加
        videosMapperCustom.addVideoLikeCount(videoId);

        //3.用户受喜欢数量增加
        usersMapper.addReceiveLikeCount(userId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void userUnLikeVideo(String userId, String videoId, String videoCreaterId) {
        //1.删除视频喜欢和用户的关联关系
        Example example=new Example(UsersLikeVideos.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);
        criteria.andEqualTo("videoId",videoId);
        usersLikeVideosMapper.deleteByExample(example);
        //2.视频喜欢数量增减
        videosMapperCustom.reduceVideoLikeCount(videoId);

        //3.用户受喜欢数量增减
        usersMapper.reduceReceiveLikeCount(userId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult queryMyLikeVideos(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);//调用这个方法后进行查询进行拦截和sql拼接limit
        List<VideosVO> list=videosMapperCustom.queryMyLikeVideos(userId);
        //System.out.println(list);
        PageInfo<VideosVO> pageInfo=new PageInfo<>(list);

        PagedResult pagedResult=new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageInfo.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageInfo.getTotal());//总记录数
        //System.out.println(pagedResult);
        return pagedResult;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedResult queryMyFollowVideos(String userId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);//调用这个方法后进行查询进行拦截和sql拼接limit
        List<VideosVO> list=videosMapperCustom.queryMyFollowVideos(userId);
        PageInfo<VideosVO> pageInfo=new PageInfo<>(list);

        PagedResult pagedResult=new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageInfo.getPages());
        pagedResult.setRows(list);
        pagedResult.setRecords(pageInfo.getTotal());//总记录数
        return pagedResult;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveComment(Comments comment) {
        String id=sid.nextShort();
        comment.setId(id);
        comment.setCreateTime(new Date());
        commentsMapper.insert(comment);
    }

    @Override
    public PagedResult getAllComments(String videoId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<CommentsVO> list = commentsMapperCustom.queryComments(videoId);

        for (CommentsVO c : list) {
            String timeAgo = TimeAgoUtils.format(c.getCreateTime());
            c.setTimeAgoStr(timeAgo);
        }

        PageInfo<CommentsVO> pageList = new PageInfo<>(list);

        PagedResult grid = new PagedResult();
        grid.setTotal(pageList.getPages());
        grid.setRows(list);
        grid.setPage(page);
        grid.setRecords(pageList.getTotal());

        return grid;
    }
}
