package com.lyl.service.Impl;

import com.lyl.dao.AccountDao;
import com.lyl.entity.Account;
import com.lyl.service.AccountService;
import com.lyl.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;

/**第一节课的内容
 * xml配置
 * <bean id="accountService" class="com.lyl.service.Impl.AccountServiceImpl"
 *  scope="" inti-method="" destroy-method="">
 * <property name="" value=""/ref=""></property>
 * </bean>
 *
 * 用于创建对象的注解
 *  @ Component
 *      作用：用于把当前类对象存入spring容器中
 *      属性：value：用于指定bean的id，不写的时候 默认值为当前类名且首字母小写
 * @ Controller 表现层
 * @ Service  业务层
 * @ Repository 持久层
 * 作用于属性和component一致
 * 用于注入数据的注解
 * @ Autowired
 *      作用:自动按照类型注入，只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功
 *          如果IOC容器中没有任何bean的类型和要注入的变量类型匹配，则报错
 *          如果IOC有多个类型匹配（service里面的dao变量有多个实现类）时，首先先按照数据类型进行划分，再对变量名进行匹配
 *      位置：
 *          变量、方法上
 *      好处:使用注解注入时，set方法就不是必须的了
 * 鉴于上面多个实现类的问题
 * @ Qualifier
 *      作用：在按照类型中注入的基础之上再按照名称注入，(与autowired)他在给类成员注入时不能单独使用，但是在给方法参数注入时可以
 *      属性:
 *          value用于指定注入bean的id
 * @ Resource
 *       作用：直接按照bean的id注入，可以单独使用
 *       属性：name用于指定注入bean的id
 *
 * 以上三个注解只能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现
 * 另外，集合类型的注入只能通过XML来实现
 * @ Value
 *      作用：用于注入基本类型和String类型的数据
 *      属性：value 值，可以使用spring中的spEL ${}
 * 用于改变作用范围的注解
 * @ Scope
 *  作用：指定bean的作用范围
 *  属性：value singleton prototype
 * 和生命周期相关的注解(了解)
 * @ PreDestroy :指定销毁方法
 * @ PostConstruct ：指定初始化方法
 */
@Service(value="accountService")
@Scope("singleton")
public class AccountServiceImpl implements AccountService {
    public AccountServiceImpl(){
        System.out.println("对象创建了");
    }
/*
第一节课的内容
//    @Autowired//这里有细节，有多个实现类的时候如何分辨，这就要求这个Dao口的名字要和对应将实现类的加入容器的标签的value（id）一致
//    @Qualifier("accountDao2")
    @Resource(name = "accountDao2")
    private AccountDao accountDao;
    public void saveAccount(){accountDao.saveAccount();}
    @PostConstruct
    public void init(){
        System.out.println("初始化");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("销毁");
    }*/
    @Resource(name="accountDao1")
    private AccountDao accountDao;
    @Autowired
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    public final void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
    //要使得事务控制和代理对象真正分离，我们的BeanFactory类创建了一个获取service代理对象的方法，该方法让service都经过代理对象，
    //此时将对应在这里的代理代码注释
    public List<Account> findAllAccount() {
        try{
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            List<Account> accounts= accountDao.findAllAccount();
            //3.提交事务
            txManager.commit();
            //4.返回结果
            return accounts;
        }catch (Exception e){
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);//这里必须要写，否则会报没有return的错误
        }finally {
            //释放连接
            txManager.release();
        }

    }

    public Account findAccountById(Integer id) {
        try{
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            Account account= accountDao.findAccountById(id);
            //3.提交事务
            txManager.commit();
            //4.返回结果
            return account;
        }catch (Exception e){
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);//这里必须要写，否则会报没有return的错误
        }finally {
            //释放连接
            txManager.release();
        }

    }

    public void saveAccount(Account account) {
        try{
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            accountDao.saveAccount(account);
            //3.提交事务
            txManager.commit();
            //4.返回结果
        }catch (Exception e){
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);//这里必须要写，否则会报没有return的错误
        }finally {
            //释放连接
            txManager.release();
        }

    }

    public void updateAccount(Account account) {
        try{
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            accountDao.updateAccount(account);
            //3.提交事务
            txManager.commit();
            //4.返回结果
        }catch (Exception e){
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);//这里必须要写，否则会报没有return的错误
        }finally {
            //释放连接
            txManager.release();
        }

    }

    public void deleteAccount(Integer id) {
        try{
            //1.开启事务
            txManager.beginTransaction();
            //2.执行操作
            accountDao.deleteAccount(id);
            //3.提交事务
            txManager.commit();
            //4.返回结果
        }catch (Exception e){
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);//这里必须要写，否则会报没有return的错误
        }finally {
            //释放连接
            txManager.release();
        }

    }

    /**
     * 事务控制要放在业务层
     * 要将conn事务绑定起来
     * 需要使用ThreadLocal把connection和当前线程绑定起来，从而使一个线程中只有一个能控制事务的对象
     * @param sourceName 转出账户名称
     * @param targetName    转入账户名称
     * @param money 转账金额
     */
    public void transfer(String sourceName, String targetName, Float money) {
       /* try{*/
            /*//1.开启事务
            txManager.beginTransaction();*/
            //2.执行操作
            Account source=accountDao.findAccountByName(sourceName);//获取一个conn连接
            Account target=accountDao.findAccountByName(targetName);//获取一个
            source.setMoney(source.getMoney()-money);
            target.setMoney(target.getMoney()+money);
            accountDao.updateAccount(source);//获取一个
            //int i=1/0;
            accountDao.updateAccount(target);//获取一个
           /* //3.提交事务
            txManager.commit();
            //4.返回结果*/

       /* }catch (Exception e){
            //5.回滚操作
            txManager.rollback();
            throw new RuntimeException(e);//这里必须要写，否则会报没有return的错误
        }finally {
            //释放连接
            txManager.release();
        }*/

    }
    public void aop(){
        System.out.println("aop？？？");
    }
}
