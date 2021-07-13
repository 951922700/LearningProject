package com.lyl.Controller;

import com.lyl.exception.UserNotExistException;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理器的一个注解
 */
@ControllerAdvice
public class MyExceptionHandle {

    /**
     * 浏览器和客户端都是返回json
     * @param e
     * @return
     */
  /*  @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Map<String,Object> handleException(Exception e){
        Map<String,Object> map=new HashMap<>();
        map.put("code","user not exist");
        map.put("msg",e.getMessage());
        return map;
    }*/

    /**
     * 转发到/error进行自适应效果处理
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        Object user = request.getSession().getAttribute("loginUser");
        request.setAttribute("javax.servlet.error.status_code",500);//设置自己的状态码
        map.put("code","user not exist");
        map.put("message",e.getMessage());
        request.setAttribute("ext",map);
        return "forward:/error";//转发到/error让他自适应
    }
}
