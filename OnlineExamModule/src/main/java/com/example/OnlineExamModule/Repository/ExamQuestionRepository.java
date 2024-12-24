package com.example.OnlineExamModule.Repository;

import com.example.OnlineExamModule.Entity.Exam;
import com.example.OnlineExamModule.Entity.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamQuestionRepository extends JpaRepository<ExamQuestion,Long> {
    List<ExamQuestion> findByExam(Exam exam);
}
