package com.example.OnlineExamModule.Repository;

import com.example.OnlineExamModule.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam,Long> {
}
