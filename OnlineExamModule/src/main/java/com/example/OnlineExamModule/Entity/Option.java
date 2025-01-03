package com.example.OnlineExamModule.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="options")
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "option_text", nullable = false)
    private String optionText;

//    @Column(name = "option_label", nullable = false)
//    private String optionLabel;

    // Getters and Setters
    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

//    public String getOptionLabel() {
//        return optionLabel;
//    }
//
//    public void setOptionLabel(String optionLabel) {
//        this.optionLabel = optionLabel;
//    }
}
