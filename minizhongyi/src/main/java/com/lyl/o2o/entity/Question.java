package com.lyl.o2o.entity;

import java.io.Serializable;

public class Question implements Serializable {
    private Integer cid;//主键，也是收藏要用到的id

    private Integer id;//第几章问题，可能有多个，每组下有题目，还有选项、答案、解析、解析图片

    private String problem;

    private String choose;//-->ABCD四个选项

    private String answer;//ABCD

    private String analyse;//解析

    private String img;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getChoose() {
        return choose;
    }

    public void setChoose(String choose) {
        this.choose = choose;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnalyse() {
        return analyse;
    }

    public void setAnalyse(String analyse) {
        this.analyse = analyse;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Question{" +
                "cid=" + cid +
                ", id=" + id +
                ", problem='" + problem + '\'' +
                ", choose='" + choose + '\'' +
                ", answer='" + answer + '\'' +
                ", analyse='" + analyse + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
