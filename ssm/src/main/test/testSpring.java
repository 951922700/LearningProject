import com.lyl.dao.AccountDao;
import com.lyl.domain.Account;
import com.lyl.service.AccountService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class testSpring {
    @Test
    public void run1(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService as =(AccountService) ac.getBean("accountService");
        as.findAll();
    }
    @Test
    public void testMybatis() throws Exception {
        //加载配置文件
        InputStream in=Resources.getResourceAsStream("sqlMapConfig.xml");

        //创建SqlSessionFactory
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        //创建sqlsession对象
        SqlSession session=factory.openSession();
        //获取代理对象
        AccountDao ad = session.getMapper(AccountDao.class);
        //查询所有
        List<Account> accountList = ad.findAll();
        for (Account account:accountList)
            System.out.println(account);
        /**
         * 提交事务，否则保存不能运行
         */
        session.commit();
        //关闭资源
        session.close();
        in.close();
    }
    @Test
    public void testservice(){
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService as =(AccountService) ac.getBean("accountService");
        List<Account> accounts = as.findAll();
        System.out.println(accounts.get(0));
    }
}
