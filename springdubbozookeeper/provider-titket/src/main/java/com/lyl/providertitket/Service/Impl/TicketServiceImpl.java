package com.lyl.providertitket.Service.Impl;

import com.lyl.providertitket.Service.TicketService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;


/**
 * 导入的是dubbo的service 将服务发布出去
 * 还要放在spring容器中
 */
@Component
@Service
public class TicketServiceImpl implements TicketService {

    @Override
    public String getTicket() {
        return "<厉害了我的国>";
    }
}
