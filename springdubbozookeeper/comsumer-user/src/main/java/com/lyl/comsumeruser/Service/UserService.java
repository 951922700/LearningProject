package com.lyl.comsumeruser.Service;

import com.lyl.providertitket.Service.TicketService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

//懒得弄接口了
@Service
public class UserService {

    @Reference
    TicketService ticketService;

    public void hello(){
        String ticket = ticketService.getTicket();
        System.out.println(ticket);
    }
}
