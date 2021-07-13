package com.lyl.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("启动了");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("销毁了");
    }
}
