package com.lyl.o2o.controller;

import com.lyl.o2o.entity.Question;
import com.lyl.o2o.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/query",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> queryById(Question question){
        Map<String,Object> modelMap=new HashMap<>();
        if(question.getId()==null){
            modelMap.put("success",false);
            modelMap.put("errMsg","没有id");
            return modelMap;
        }

        try {
            List<Question> questions = questionService.queryById(question);
            modelMap.put("success",true);
            modelMap.put("data",questions);
            modelMap.put("length",questions.size());
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success",false);
            modelMap.put("errMsg",e.toString());
            return modelMap;
        }
        return modelMap;
    }
}
