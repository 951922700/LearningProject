import com.lyl.domain.Account;
import com.lyl.service.IAccountService;
import com.lyl.service.impl.AccountService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class test {
    ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
    //当我们使用proxy动态代理的时候，这里要用接口，否则会报错
    IAccountService as=(IAccountService) ac.getBean("accountService");
    @Test
    public void transef(){
        as.transfer("aaa","bbb",100f);

    }
}
