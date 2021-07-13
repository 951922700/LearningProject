package com.lyl.juc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
class User{
    private Integer id;
    private String userName;
    private int age;
    /*
         User user=new User();
         user.setAge(1).setId(1).setUserName("1");
     */
}

/**
 * 找偶数ID  年龄大于24 且用户名转为大写  字母倒序
 * 只输出一个
 */
public class StreamDemo {
    public static void main(String[] args) {
        User u1=new User(11,"a",23);
        User u2=new User(12,"b",24);
        User u3=new User(13,"c",22);
        User u4=new User(14,"d",28);
        User u5=new User(16,"e",26);

        List<User> list= Arrays.asList(u1,u2,u3,u4,u5);
        list.stream()
                .filter(u->{return u.getId()%2==0;})
                .filter(u->{return u.getAge()>24;})
                .map(user -> {return user.getUserName().toUpperCase();})
                .sorted(((o1, o2) -> {return o2.compareTo(o1);}))
                .limit(1)
                .collect(Collectors.toList()).forEach(System.out::println);
    }

    private static void text() {
     Function<String,Integer> function=(s)->{
       return 1024;
     };
     System.out.println(function.apply("1"));
     Predicate<String> predicate=(s)->{
         return true;
     };
     System.out.println(predicate.test("1"));
     Consumer<String> consumer=new Consumer<String>() {
         @Override
         public void accept(String s) {

         }
     };
     Supplier<String> supplier=new Supplier<String>() {
         @Override
         public String get() {
             return null;
         }
     };
    }
}
