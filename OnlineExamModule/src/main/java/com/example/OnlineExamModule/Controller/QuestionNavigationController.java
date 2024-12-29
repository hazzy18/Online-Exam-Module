package com.example.OnlineExamModule.Controller;

import com.example.OnlineExamModule.Entity.ExamQuestion;
import com.example.OnlineExamModule.Entity.Question;
import com.example.OnlineExamModule.Entity.StudentExamEntity;
import com.example.OnlineExamModule.Service.QuestionNavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exams/{examId}/questions")
public class QuestionNavigationController {
    @Autowired
    private QuestionNavigationService questionNavigationService;

    @GetMapping
    public ResponseEntity<List<ExamQuestion>> getQuestionsForExam(@PathVariable Long examId){
       List<ExamQuestion> questions= questionNavigationService.getQuestionsForExam(examId);
       return ResponseEntity.ok(questions);
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long quetsionId){
        Question question=questionNavigationService.getQuestionById(quetsionId);
        return ResponseEntity.ok(question);

        }
    @GetMapping("/student/{studentExamId}")
    public  ResponseEntity<StudentExamEntity> getStudentExam(@PathVariable Long studentExamId){
        StudentExamEntity studentExam=questionNavigationService.getStudentExam(studentExamId);
        return ResponseEntity.ok(studentExam);
    }

    @PostMapping("/submit/{studentExamId}")
    public ResponseEntity<StudentExamEntity> submitExam(@PathVariable Long studentExamId, @RequestBody Map<String, Map<Long, String>> answers) {
        Map<Long, String> mcqAnswers = answers.getOrDefault("mcqAnswers", Map.of());
        Map<Long, String> programmingAnswers = answers.getOrDefault("programmingAnswers", Map.of());
        questionNavigationService.submitExam(studentExamId, mcqAnswers, programmingAnswers);
        StudentExamEntity studentExam = questionNavigationService.getStudentExam(studentExamId);
        return ResponseEntity.ok(studentExam);
    }

    @PostMapping("/save-answer")
    public ResponseEntity<Void> saveAnswer(@RequestBody Map<String,String> answer){
        Long studentExamId=Long.parseLong(answer.get("studentExamId"));
        Long questionId=Long.parseLong(answer.get("questionId"));
        String selectedOption=answer.get("selectedOption");
        questionNavigationService.saveAnswer(studentExamId, questionId, selectedOption);
        return ResponseEntity.ok().build();
    }

}
