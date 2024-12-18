package com.example.OnlineExamModule.Repository;

import com.example.OnlineExamModule.Entity.StudentProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfileEntity,Long> {
    boolean existsByEmail(String email);

}

