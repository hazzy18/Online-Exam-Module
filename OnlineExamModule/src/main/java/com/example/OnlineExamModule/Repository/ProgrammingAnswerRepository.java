package com.example.OnlineExamModule.Repository;

import com.example.OnlineExamModule.Entity.ProgrammingAnswerEntity;
import com.example.OnlineExamModule.Entity.StudentExamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProgrammingAnswerRepository extends JpaRepository<ProgrammingAnswerEntity, Long> {
    List<ProgrammingAnswerEntity> findByStudentExam(StudentExamEntity studentExam);
}
