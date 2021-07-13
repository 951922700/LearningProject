package com.lyl;

import com.lyl.Bean.Employee;
import com.lyl.Bean.Person;
import com.lyl.Bean.Book;
import com.lyl.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.xml.ws.soap.Addressing;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * springboot单元测试
 *
 * 可以在测试期间很方便的进行类似编码一样进行自动注入等容器功能
 */
@SpringBootTest
class SpringBootLearnApplicationTests {
    @Autowired
    Person person;
    @Autowired
    EmployeeMapper employeeMapper;
//    @Autowired
//    RabbitTemplate rabbitTemplate;
    @Autowired
    AmqpAdmin amqpAdmin;//用来创建exchange和queue

    @Autowired
    JavaMailSenderImpl javaMailSender;//发送邮件

    @Test
    public void createExchange(){
        amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
        System.out.println("创建成功");
    }
    @Test
    void contextLoads() {
        System.out.println(person);
    }

    @Test
    public void testEm()
    {
        try {
            Employee employee=new Employee();
            employee.setId(2);
            employee.setLastName("粑粑");
            employee.setDid(1);
            employee.setEmail("22");
            employee.setGender(1);
            employeeMapper.insertEmp(employee);
            Employee employee1 = employeeMapper.gettt(2);
            System.out.println(employee1);
            employeeMapper.delete(2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试消息
     * 1.点对点
     * 广播也是用这个方法 只是exchange改成fanout
     */
  /*  @Test
    public void contextLoad(){
        //Message需要自己定义
        //rabbitTemplate.send(exchange,routeKey,Message);



        //rabbitTemplate.convertAndSend(exchange,routeKey,object);
        Map<String,Object> map=new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("hellowordl",123));
        Book book=new Book();
        book.setBookName("我爱读书");
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",book);
    }
    @Test
    public void recieve(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }*/

   @Test
   public void sendMail(){
       //简单邮件
       SimpleMailMessage message=new SimpleMailMessage();
       //邮件设置
       message.setSubject("通知：紧急通知");
       message.setText("今晚抓猫，收到回复");
       message.setTo("940900313@qq.com");//940900313
       message.setFrom("951922700@qq.com");
       javaMailSender.send(message);
   }

   @Test
   public void sendComplexMail(){
       //带附件的邮件
       try {
           MimeMessage mimeMessage=javaMailSender.createMimeMessage();
           MimeMessageHelper Helper=new MimeMessageHelper(mimeMessage,true);//true表明有文件
           Helper.setSubject("通知：紧急通知");
           Helper.setText("<b style='color:red'>今晚抓猫，收到回复</b>",true);
           Helper.setTo("940900313@qq.com");
           Helper.setFrom("951922700@qq.com");

           //上传文件
           Helper.addAttachment("猫",new File("F:\\chrome\\download\\123.jpeg"));
           javaMailSender.send(mimeMessage);
       } catch (MessagingException e) {
           e.printStackTrace();
       }
   }
}
