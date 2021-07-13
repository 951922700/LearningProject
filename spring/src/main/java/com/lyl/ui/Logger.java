package com.lyl.ui;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @ Aspect
 *
 * @ Before
 */
@Component("logger")
@Aspect//表示当前类是一个切面类
public class Logger {
    /**
     * 配置切入点,注解aop要在xml加标签,想使用纯注解的时候直接在配置类@EnableAspectJAutoProxy
     */
    @Pointcut("execution(* com.lyl.service.Impl.*.*(..))")
    public void pt2(){}

    /**
     * 用于打印日志:计划让其在切入点方法执行之前执行（切入点方法就是业务层方法）
     */
    @Before("pt2()")
    public void printLog(){
        System.out.println("Logger类的printLog开始记录日志~~~~~");
    }

    /**
     * 最终通知
     */
    @After("pt2()")
    public void afterLogger(){
        System.out.println("最终通知");
    }

    /**
     * 后置通知
     */
    @AfterReturning("pt2()")
    public void afterReturningLog(){
        System.out.println("后置通知");
    }
    /**
     * 异常通知
     */
    @AfterThrowing("pt2()")
    public void afterThrowingLog(){
        System.out.println("异常通知");
    }

    /**
     * 下面整个是环绕通知：
     * 问题：
     *  当我们配置了环绕通知之后，切入点方法没有执行，而通知方法执行了
     * 分析：
     *  发现没有切入点的方法调用
     * 解决：
     *      Spring框架为我们提供了一个接口，ProceedingJoinPoint，接口中有一个方法proceed（），此方法就相当于明确
     *      调用切入点方法，该接口可以作为环绕通知的方法参数，在程序执行时，spring框架
     *      会我们提供该接口的实现类供我们使用
     */
    @Around("pt2()")
    public Object aroundLog(ProceedingJoinPoint pjp){
        Object rtValue=null;
        try {
            Object[] args=pjp.getArgs();//获取方法参数
            System.out.println("前置通知");
            rtValue=pjp.proceed(args);//明确调用切入点方法,返回一个Oject
            System.out.println("后置通知");
            //必须Throwable拦截
            return rtValue;
        } catch (Throwable throwable) {
            System.out.println("异常通知");
            throw new RuntimeException(throwable);
        }finally {
            System.out.println("最终通知");
        }

    }
}
