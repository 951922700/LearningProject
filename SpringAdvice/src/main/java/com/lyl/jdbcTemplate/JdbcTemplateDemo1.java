package com.lyl.jdbcTemplate;
import com.lyl.domain.Account;
import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * JdbcTemplate 的最基本用法
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
       /* //准备数据源：spring内置数据源
        DriverManagerDataSource ds=new DriverManagerDataSource();
        ds.setDriverClass("com.mysql.cj.jdbc.Driver");
        ds.setUser("root");
        ds.setPassword("943033940");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/spring?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8");

        //1.创建jdbcTempalte
        JdbcTemplate jt=new JdbcTemplate();
        //给gt设置数据源
        jt.setDataSource(ds);
        //执行操作
        jt.execute("insert  into account(name,money)values('ddd',1000)");*/
        ApplicationContext ac=new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate jt=(JdbcTemplate) ac.getBean("jdbcTemplate");

        //保存
        //jt.update("insert  into  account(name,money)values (?,?)","eee",3333);

        //更新
        //jt.update("update account set name=?,money=? where id=?","eee",2222,4);

        //删除
        //jt.update("delete from account where id=?","5" );

        //查询所有

        //List<Account> accounts=jt.query("select * from account where money >?",new AccountRowMapper(),1000f);
        //直接用BeanPropertyRowMapper就可以，不用上面那种自己封装
       /* List<Account> accounts=jt.query("select * from account where money >?",new BeanPropertyRowMapper<Account>(Account.class),1000f);
        for (Account a:accounts){
            System.out.println(a);
        }*/

        //查询一个
        List<Account> accounts=jt.query("select * from account where money =?",new BeanPropertyRowMapper<Account>(Account.class),1);
        System.out.println(accounts.isEmpty()?"没有内容":accounts.get(0));

        //返回一列数目,第二个参数指定返回什么类型的字节码文件
        Integer count=jt.queryForObject("select count(*) from account where money>?",Integer.class,1000f);
    }
}

/**
 * 定义Account的封装策略，
 */
class AccountRowMapper implements RowMapper<Account>{
    /**
     * 把结果集中的数据封装到Acount中，然后由spring把每个Account加到集合中
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account=new Account();
        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setMoney(resultSet.getFloat("money"));
        return account;
    }
}
