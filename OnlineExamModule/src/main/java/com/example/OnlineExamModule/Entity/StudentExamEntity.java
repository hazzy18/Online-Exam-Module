package com.example.OnlineExamModule.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "student_exam")
public class StudentExamEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentExamId;

    @ManyToOne
    @JoinColumn(name= "student_id",nullable = false)
    private StudentProfileEntity student;

    @ManyToOne
    @JoinColumn(name = "exam_id",nullable = false)
    private Exam exam;

    @Column(name = "score",nullable = false)
    private int score;

    @Column(name = "passed",nullable = false)
    private boolean passed;

    @Column(name = "submitted_at",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date submittedAt=new Date();
    //Getters and Setters

    public Long getStudentExamId(){
        return studentExamId;
    }
    public void setStudentExamId(Long studentExamId){
        this.studentExamId=studentExamId;
    }

    public StudentProfileEntity getStudent(){
        return student;
    }

    public void setStudent(StudentProfileEntity student){
        this.student=student;
    }

    public Exam getExam(){
        return exam;
    }
    public void setExam(Exam exam){
        this.exam=exam;
    }

    public int getScore(){
        return score;
    }
    public  void setScore(int score){
        this.score=score;
    }
    public boolean isPassed(){
        return passed;
    }
    public void setPassed(boolean passed){
        this.passed=passed;
    }
    public Date getSubmittedAt(){
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt){
        this.submittedAt=submittedAt;
    }
}
