package com.lyl.mapper;

import java.util.List;

import com.lyl.pojo.Comments;
import com.lyl.pojo.vo.CommentsVO;
import com.lyl.utils.MyMapper;

public interface CommentsMapperCustom extends MyMapper<Comments> {
	
	public List<CommentsVO> queryComments(String videoId);
}