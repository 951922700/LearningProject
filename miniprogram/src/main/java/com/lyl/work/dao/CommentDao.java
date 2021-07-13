package com.lyl.work.dao;

import com.lyl.work.entity.Comment;

import java.util.List;

public interface CommentDao {
    /**
     * 要实现的功能，
     * 1.根据chatId查询该文章的所有评论内容
     * 2.评论功能，创建一条评论要求传入chatId commentId 等信息
     */
    List<Comment> queryAllById(String chatId);

    void createComment(Comment comment);


    /**
     * 根据commentId 查图片数量
     */
    Integer queryImgNum(String commentId);

    /**
     * 根据commentId 更新图片数量
     */
    void addImgNum(Comment comment);
}
