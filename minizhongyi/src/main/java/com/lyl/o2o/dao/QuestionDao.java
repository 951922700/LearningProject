package com.lyl.o2o.dao;

import com.lyl.o2o.entity.Question;

import java.util.List;

public interface QuestionDao {
    /**
     * 查询单元下全部题目
     * @param question
     * @return
     */
    List<Question> queryById(Question question);

    /**
     * 根据cid查询某个题目
     */
    Question queryByCid(Question question);
}
