package com.example.OnlineExamModule.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="exam_questions")
public class ExamQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="exam_id",nullable = false)
    private Exam exam;
    //mapped with Entity

    @ManyToOne
    @JoinColumn(name = "question_id",nullable = false)
    private Question question;

    //getters and setters

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id=id;
    }
    public Exam getExam(){
        return exam;
    }
    public void setExam(Exam exam){
        this.exam=exam;
    }
    public Question getQuestion(){
        return question;
    }
    public void setQuestion(Question question)
    {
        this.question=question;
    }
}
