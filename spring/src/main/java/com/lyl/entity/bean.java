package com.lyl.entity;

import java.util.*;

public class bean {
    private String name;
    private Integer age;
    private Date date;
    private String [] myStr;
    private List<String> muList;
    private Set<String> mySet;
    private Map<String,String> myMap;
    private Properties myProps;

    public bean(){

    }
    public bean(String name,Integer age,Date date){
      this.name=name;
      this.age=age;
      this.date=date;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init(){
        System.out.println("初始化成功");
    }
    public void destroy(){
        System.out.println("销毁了");
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public void setMyStr(String[] myStr) {
        this.myStr = myStr;
    }

    public void setMuList(List<String> muList) {
        this.muList = muList;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setMyProps(Properties myProps) {
        this.myProps = myProps;
    }

    @Override
    public String toString() {
        return "bean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date +
                ", myStr=" + Arrays.toString(myStr) +
                ", muList=" + muList +
                ", mySet=" + mySet +
                ", myMap=" + myMap +
                ", myProps=" + myProps +
                '}';
    }

}
