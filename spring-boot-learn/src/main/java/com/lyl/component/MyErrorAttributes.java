package com.lyl.component;

import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 给容器加入自己定义的ErrorAttributes
 */
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, includeStackTrace);
        map.put("company","lyl");
        Map<String, Object> ext= (Map<String, Object>) webRequest.getAttribute("ext",0);
        map.put("ext",ext);
        return map;
    }
    
}
