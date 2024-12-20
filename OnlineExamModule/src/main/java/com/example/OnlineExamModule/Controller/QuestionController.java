package com.example.OnlineExamModule.Controller;

import com.example.OnlineExamModule.DTO.QuestionDto;
import com.example.OnlineExamModule.Entity.Question;
import com.example.OnlineExamModule.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Better than @Controller for APIs
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * Add a new question
     * @param questionDto DTO containing question details
     * @return Created question
     */
    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody QuestionDto questionDto) {
        Question question = questionService.createQuestion(questionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    /**
     * Get questions by category name
     * @param categoryName Name of the category
     * @return List of questions belonging to the given category
     */
    @GetMapping("/{categoryName}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String categoryName) {
        List<Question> questions = questionService.getQuestionsByCategory(categoryName);
        return ResponseEntity.ok(questions);
    }
}
