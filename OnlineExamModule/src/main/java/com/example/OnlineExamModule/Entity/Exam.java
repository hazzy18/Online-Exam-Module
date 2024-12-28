package com.example.OnlineExamModule.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    @Column(name="exam_name",nullable = false)
    private String examName;

    @Column(name = "total_questions",nullable = false)
    private int totalQuestions;

    @Column(name="passing_criteria",nullable = false)
    private int passingCriteria;

    @Column(name="duration",nullable=false)
    private int duration;//duration in mins

    @Column(name="created_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt=new Date();
    //getters and setters

    public Long getExamId(){
        return examId;
    }
    public void setExamId(Long examId){
        this.examId=examId;
    }
    public String getExamName(){
        return examName;
    }
    public void setExamName(String examName){
        this.examName=examName;
    }
    public int getTotalQuestions(){
        return totalQuestions;
    }
    public void setTotalQuestions(int totalQuestions){
        this.totalQuestions=totalQuestions;
    }
    public int getPassingCriteria(){
        return passingCriteria;
    }
    public void setPassingCriteria(int passingCriteria){
        this.passingCriteria=passingCriteria;
    }
    public Date getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(Date createdAt){
        this.createdAt=createdAt;
    }
    public int getDuration(){
        return duration;
    }
    public void setDuration(int duration){
        this.duration=duration;
    }
}
