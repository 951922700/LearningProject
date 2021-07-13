package com.lyl.o2o.service;

import com.lyl.o2o.entity.Question;

import java.util.List;

public interface QuestionService {
    List<Question> queryById(Question question);

    /**
     * 根据cid查询某个题目
     */
    Question queryByCid(Question question);
}
