package com.lyl.work.service.impl;

import com.lyl.work.dao.CommentDao;
import com.lyl.work.entity.Comment;
import com.lyl.work.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> queryAllById(String chatId) {
        return commentDao.queryAllById(chatId);
    }

    @Override
    public void createComment(Comment comment) {
        commentDao.createComment(comment);
    }

    @Override
    public Integer queryImgNum(String commentId) {
        return commentDao.queryImgNum(commentId);
    }

    @Override
    public void addImgNum(Comment comment) {
        commentDao.addImgNum(comment);
    }
}
