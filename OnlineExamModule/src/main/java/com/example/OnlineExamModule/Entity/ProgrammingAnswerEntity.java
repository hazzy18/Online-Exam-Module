package com.example.OnlineExamModule.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "programming_answers")
public class ProgrammingAnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_exam_id", nullable = false)
    private StudentExamEntity studentExam;

    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(name = "answer_text", columnDefinition = "TEXT", nullable = true)
    private String answerText;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StudentExamEntity getStudentExam() {
        return studentExam;
    }

    public void setStudentExam(StudentExamEntity studentExam) {
        this.studentExam = studentExam;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }
}
