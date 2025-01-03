package com.example.OnlineExamModule.Controller;

import com.example.OnlineExamModule.DTO.QuestionDto;
import com.example.OnlineExamModule.Entity.Question;
import com.example.OnlineExamModule.Service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController // Better than @Controller for APIs
@RequestMapping("/api/questions")
//@Api(value = "Question Management System", description = "Operations pertaining to questions in the Online Exam Module")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * Add a new question
     * @param questionDto DTO containing question details
     * @return Created question
     */
    @PostMapping
    @Operation(summary = "Add a new question", description = "Adds a new question to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created a new question"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<Question> addQuestion(@RequestBody QuestionDto questionDto) {
        Question question = questionService.createQuestion(questionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(question);
    }

    /**
     * Get questions by category name
     * @param categoryName Name of the category
     * @return List of questions belonging to the given category
     */
    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByCategory(@PathVariable String categoryName) {
        List<QuestionDto> questions = questionService.getQuestionsByCategory(categoryName);
        return ResponseEntity.ok(questions);

        //---------------------------------
        //I am returning question entity but need to return options too
        /// only questions dto should have options for returning
    }
    @GetMapping("/difficulty/{difficultyLevel}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByDifficulty(@PathVariable String difficultyLevel) {
        List<QuestionDto> questions = questionService.getQuestionsByDifficulty(difficultyLevel);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/type/{questionType}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByType(@PathVariable String questionType) {
        List<QuestionDto> questions = questionService.getQuestionsByType(questionType);
        return ResponseEntity.ok(questions);
    }
    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        List<QuestionDto> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }
}
