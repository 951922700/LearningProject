package com.lyl.Bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 降配置文件中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties.告诉springboot将本类中的所有属性和配置文件中相关的配置进行绑定  默认从全局配置中获取值
 *  prefix:"person" 配置文件中哪个下面的所有属性进行映射
 *
 *  只有这个组件是容器中的组件，才能使用容器提供的@ConfigurationProperties功能
 * @Value也能注入属性
 * @Value("字面量/${key}从配置文件、环境变量取值/#{}--->SPEL")
 *
 * 在使用@Configuraqtion的情况下可以用
 * @Validated 进行数据校验  在字段前加@Email会判断是否是邮箱格式  支持JR303数据校验
 *
 * @PropertySource(value="classpath:person.properties")加载指定的配置文件
 *
 * 在main那个类上面加上@ImportResource(locations={"classpath:bean.xml"})用来加载一个之前配置IOC的bean XML文件
 * 
 * springboot推荐使用全注解配置
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {
    //@Value("${person.last-name}")
    private String lastName;
    //@Value("#{1*12}")
    private Integer age;
    //@Value("true")
    private Boolean boss;
    private Date birth;

    private Map<String,Object> maps;
    private List<Object> lists;

    private Dog dog;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
