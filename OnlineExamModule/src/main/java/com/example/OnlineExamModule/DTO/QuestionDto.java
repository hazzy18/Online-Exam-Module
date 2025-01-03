package com.example.OnlineExamModule.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class QuestionDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long questionId;
    private String questionText;
    private String categoryName;
    private String correctOption;
    private List<String> options;
    private String referenceAnswer;
    private String difficultyLevel;
    private String questionType; // "MCQ" or "PROGRAMMING"

    // Getters and Setters

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }


    public Long getQuestionId(){
        return questionId;
    }
    public void setQuestionId(Long questionId){
        this.questionId=questionId;
    }
    public String getReferenceAnswer() {
        return referenceAnswer;
    }

    public void setReferenceAnswer(String referenceAnswer) {
        this.referenceAnswer = referenceAnswer;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}
