package com.example.OnlineExamModule.DTO;

import java.util.List;

public class ExamDto {

    private String examName;
    private int totalQuestions;
    private int passingCriteria;
    private List<Long> questionIds;

    //getters and setters
    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getPassingCriteria() {
        return passingCriteria;
    }

    public void setPassingCriteria(int passingCriteria) {
        this.passingCriteria = passingCriteria;
    }

    public List<Long> getQuestionIds(){
        return questionIds;
    }
    public void setQuestionIds(List<Long> questionIds){
        this.questionIds=questionIds;
    }
}
