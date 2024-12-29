package com.example.OnlineExamModule.Repository;

import com.example.OnlineExamModule.Entity.Category;
import com.example.OnlineExamModule.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {
    List<Question> findByCategory(Category category);

    List<Question> findByDifficultyLevel(String difficultyLevel);

    List<Question> findByQuestionType(String questionType);
}
