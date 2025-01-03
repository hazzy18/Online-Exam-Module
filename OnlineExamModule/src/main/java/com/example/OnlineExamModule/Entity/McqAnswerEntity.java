package com.example.OnlineExamModule.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="mcq_answers")
public class McqAnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_exam_id", nullable = false)
    private StudentExamEntity studentExam;

    @Column(name = "question_id", nullable = false)
    private Long questionId;

    @Column(name = "selected_option", nullable = true)
    private String selectedOption;

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

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

}
