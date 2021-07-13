package com.lyl.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * 链接的工具类，它用于从数据源中获取一个链接，并且实现和线程的绑定
 */
@Component
public class ConnectionUtils {
    private ThreadLocal<Connection> t1=new ThreadLocal<Connection>();
    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取当前线程上的链接
     */
    public Connection getThreadConnection(){

        try {
            //1.先从ThreadLocal上获取链接
            Connection conn=t1.get();
            //2.判断当前线程上是否有链接
            if(conn==null){
                //3.从数据源中获取一个连接，并且存入ThreadLocal
                conn=dataSource.getConnection();
                t1.set(conn);
            }
            //4.返回当前线程上的连接
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 解除连接和线程的绑定
     * 为什么要做这一步呢，如果不做，当我们TransactonManager调用close方法却没有解除线程和连接的关系的时候
     * 连接被丢回数据池，然后当我们再次调用开启事务get连接的时候，ThreadLocal上的conn是不为null的但是却不能用，因为
     * 被丢回去了，所以当我们想正确使用的时候，就在丢连接去数据池的时候将它与线程的绑定解除
     */
    public void removeConnection(){
        t1.remove();
    }
}
