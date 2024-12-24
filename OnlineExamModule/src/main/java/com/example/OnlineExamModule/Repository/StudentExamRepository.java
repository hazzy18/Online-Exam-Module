package com.example.OnlineExamModule.Repository;

import com.example.OnlineExamModule.Entity.Exam;
import com.example.OnlineExamModule.Entity.StudentExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentExamRepository extends JpaRepository<StudentExamEntity,Long> {
    List<StudentExamEntity> findByExam(Exam exam);
}
