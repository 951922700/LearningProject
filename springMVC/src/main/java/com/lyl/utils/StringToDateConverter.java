package com.lyl.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 注意导入的类
 * 自定义类型转换器
 */
public class StringToDateConverter implements Converter<String,Date> {
    /**
     * s 是 传入进来的字符串
     * @param s
     * @return
     */
    @Override
    public Date convert(String s) {
        //判断
        if (s.isEmpty()){
            throw new RuntimeException("请您传入数据");
        }
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        try{
            return  df.parse(s);
        }catch (Exception ex){
            throw new RuntimeException("数据类型转换出现错误");
        }


    }
}
