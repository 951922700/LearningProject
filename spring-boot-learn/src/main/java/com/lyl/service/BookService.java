package com.lyl.service;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.lyl.Bean.Book;

@Service
public class BookService {
    /**
     * 监听消息队列 一旦有消息调用方法   要去主配置类开启
     * @param book
     */
   /* @RabbitListener(queues = "atguigu.news")
    public void receive(Book book){
        System.out.println("收到消息"+book);
    }

    @RabbitListener(queues = "atguigu")
    public void receive(Message message){
        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());
    }*/
}
