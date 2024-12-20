package com.example.OnlineExamModule.Repository;

import com.example.OnlineExamModule.Entity.Option;
import com.example.OnlineExamModule.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<Option,Long> {
    List<Option> findByQuestion(Question question);
}
