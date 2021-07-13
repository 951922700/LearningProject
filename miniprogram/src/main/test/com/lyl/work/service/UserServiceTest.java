package com.lyl.work.service;

import com.lyl.work.BaseTest;
import com.lyl.work.entity.Comment;
import com.lyl.work.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceTest extends BaseTest {
    @Autowired
    private UserService userService;

    @Autowired
    private ChatWallService chatWallService;

    @Autowired
    private CommentService commentService;

    @Test
    public void test(){
      /*  List<User> users=userService.queryUserAll();
        for (User user:users)
            System.out.println(user);*/
    }

    @Test
    public void testqueryone(){
        /*User user =userService.queryUserById("1");
        System.out.println(user);*/
        /*int num=chatWallService.queryImgNum("2");
        System.out.println(num);*/
    }

    @Test
    public void testcomment(){
       /* List<Comment> comments = commentService.queryAllById("2");
        for (Comment comment:comments)
            System.out.println(comment);*/
    }
}
