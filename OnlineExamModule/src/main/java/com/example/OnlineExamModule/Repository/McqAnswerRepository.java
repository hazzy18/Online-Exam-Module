package com.example.OnlineExamModule.Repository;

import com.example.OnlineExamModule.Entity.McqAnswerEntity;
import com.example.OnlineExamModule.Entity.StudentExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface McqAnswerRepository extends JpaRepository<McqAnswerEntity, Long> {
    List<McqAnswerEntity> findByStudentExam(StudentExamEntity studentExam);
}
