package com.lyl.work.service;

import com.lyl.work.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> queryAllById(String chatId);

    void createComment(Comment comment);

    Integer queryImgNum(String commentId);

    void addImgNum(Comment comment);
}
