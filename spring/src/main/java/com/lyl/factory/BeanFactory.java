package com.lyl.factory;

import com.lyl.entity.Account;
import com.lyl.service.AccountService;
import com.lyl.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
@Component(value = "beanFactory")
public class BeanFactory {
    @Autowired
    private  AccountService accountService;
    @Autowired
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }
    //如果用xml配置这里set方法要用final修饰
    public final void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 获取service代理对象
     * @return
     */
    @Bean(name="proxyAccountService")
    public AccountService getAccountService(){
        return (AccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader()
                , accountService.getClass().getInterfaces(), new InvocationHandler() {
                    /**
                     * 添加事务的支持
                     * @param proxy
                     * @param method
                     * @param args
                     * @return
                     * @throws Throwable
                     */
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object returnValue=null;
                        try{
                            //1.开启事务
                            txManager.beginTransaction();
                            //2.执行操作
                            returnValue=method.invoke(accountService,args);
                            //3.提交事务
                            txManager.commit();
                            //4.返回结果
                            return  returnValue;
                        }catch (Exception e){
                            //5.回滚操作
                            txManager.rollback();
                            throw new RuntimeException(e);//这里必须要写，否则会报没有return的错误
                        }finally {
                            //释放连接
                            txManager.release();
                        }
                    }
                });

    }
}
