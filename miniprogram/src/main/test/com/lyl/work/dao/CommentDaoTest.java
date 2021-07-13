package com.lyl.work.dao;

import com.lyl.work.BaseTest;
import com.lyl.work.entity.Comment;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class CommentDaoTest extends BaseTest {
    @Autowired
    private CommentDao commentDao;

    @Test
    public void test(){
        /*List<Comment> comments = commentDao.queryAllById("2");
        for (Comment comment:comments)
            System.out.println(comment);*/
        Comment comment=new Comment();
        comment.setCommentId("1");
        comment.setImgNum(2);
        //comment.setCommentBelongChatId("3");
        // comment.setCommentContent("哈哈哈哈挺好啊");
        //comment.setCreateTime(new Date());
        //commentDao.createComment(comment);

        System.out.println(commentDao.queryImgNum("1"));
        commentDao.addImgNum(comment);
    }
}
