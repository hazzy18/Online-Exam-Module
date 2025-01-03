package com.example.OnlineExamModule.Entity;

import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

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

    @Column(name = "status", nullable = false)
    private String status; // e.g., "IN_PROGRESS", "SUBMITTED"
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

//    @OneToMany(mappedBy = "studentExam", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<McqAnswerEntity> mcqAnswers= new ArrayList<>();
//
//    @OneToMany(mappedBy = "studentExam", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ProgrammingAnswerEntity> programmingAnswers= new ArrayList<>();





//    @ElementCollection
//    @CollectionTable(name="student_exam_answers",joinColumns = @JoinColumn(name="student_exam_id"))
//    @MapKeyColumn(name= "question_id")
//    @Column(name="selected_option")
//    private Map<Long,String> mcqAnswers= new HashMap<>();
//
//
//    @ElementCollection
//    @CollectionTable(name="student_exam_programming_answers",joinColumns =@JoinColumn(name="student_exam_id"))
//    @MapKeyColumn(name="question_id")
//    @Column(name="Programming_answer",columnDefinition = "TEXT")
//    private Map<Long,String> programmingAnswers=new HashMap<>();



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
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

//    public List<McqAnswerEntity> getMcqAnswers() {
//        return mcqAnswers;
//    }
//
//    public void setMcqAnswers(List<McqAnswerEntity> mcqAnswers) {
//        this.mcqAnswers = mcqAnswers;
//    }
//
//    public List<ProgrammingAnswerEntity> getProgrammingAnswers() {
//        return programmingAnswers;
//    }
//
//    public void setProgrammingAnswers(List<ProgrammingAnswerEntity> programmingAnswers) {
//        this.programmingAnswers = programmingAnswers;
//    }
//----------------------------------------------------------------------------------------------------------------------------------------------------------------

//    public Map<Long,String> getMcqAnswers(){
//        return mcqAnswers;
//    }
//
//    public void setMcqAnswers(Map<Long,String> mcqAnswers){
//        this.mcqAnswers=mcqAnswers;
//
//    }
//    public Map<Long,String> getProgrammingAnswers(){
//        return programmingAnswers;
//    }
//
//    public void setProgrammingAnswers(Map<Long,String> programmingAnswers){
//        this.programmingAnswers=programmingAnswers;
//    }
}
