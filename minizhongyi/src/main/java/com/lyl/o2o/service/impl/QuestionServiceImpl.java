package com.lyl.o2o.service.impl;

import com.lyl.o2o.dao.QuestionDao;
import com.lyl.o2o.entity.Question;
import com.lyl.o2o.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Override
    public List<Question> queryById(Question question) {
        return questionDao.queryById(question);
    }

    @Override
    public Question queryByCid(Question question) {
        return questionDao.queryByCid(question);
    }
}
