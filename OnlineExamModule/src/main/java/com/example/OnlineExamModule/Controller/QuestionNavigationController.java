package com.example.OnlineExamModule.Controller;

import com.example.OnlineExamModule.DTO.AnswerDetailsDto;
import com.example.OnlineExamModule.DTO.QuestionDto;
import com.example.OnlineExamModule.Entity.ExamQuestion;
import com.example.OnlineExamModule.Entity.Question;
import com.example.OnlineExamModule.Entity.StudentExamEntity;
import com.example.OnlineExamModule.Service.QuestionNavigationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/examNavigation")
public class QuestionNavigationController {
    @Autowired
    private QuestionNavigationService questionNavigationService;

    @GetMapping("/{examId}/questions")
    public ResponseEntity<List<QuestionDto>> getQuestionsForExam(@PathVariable Long examId){
       List<QuestionDto> questions= questionNavigationService.getQuestionsForExam(examId);
       return ResponseEntity.ok(questions);
    }

    @GetMapping("questions/{questionId}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable Long questionId){
        /// changes-----------
        QuestionDto question=questionNavigationService.getQuestionById(questionId);
        return ResponseEntity.ok(question);

        }
    @GetMapping("/student/{studentExamId}")
    public  ResponseEntity<StudentExamEntity> getStudentExam(@PathVariable Long studentExamId){
        StudentExamEntity studentExam=questionNavigationService.getStudentExam(studentExamId);
        return ResponseEntity.ok(studentExam);
      /// -----i have doubt in this ?
        //------i think i should add list coz one student can give many exams right
    }

//    @PostMapping("/submit/{studentExamId}")
//    public ResponseEntity<StudentExamEntity> submitExam(@PathVariable Long studentExamId, @RequestBody Map<String, Map<Long, String>> answers) {
//        Map<Long, String> mcqAnswers = answers.getOrDefault("mcqAnswers", Map.of());
//        Map<Long, String> programmingAnswers = answers.getOrDefault("programmingAnswers", Map.of());
//        questionNavigationService.submitExam(studentExamId, mcqAnswers, programmingAnswers);
//        StudentExamEntity studentExam = questionNavigationService.getStudentExam(studentExamId);
//        return ResponseEntity.ok(studentExam);
//    }

    @PostMapping("/submit/{studentExamId}")
    public ResponseEntity<StudentExamEntity> submitExam(@PathVariable Long studentExamId) {
        questionNavigationService.submitExam(studentExamId);
        StudentExamEntity studentExam = questionNavigationService.getStudentExam(studentExamId);
        return ResponseEntity.ok(studentExam);
    }


    @PostMapping("/save-answer")
    public ResponseEntity<Void> saveAnswer(@RequestBody AnswerDetailsDto answerDetails) {
        questionNavigationService.saveAnswer(answerDetails);
        return ResponseEntity.ok().build();
    }

    @PostMapping("start/{studentId}/{examId}")
    public ResponseEntity<StudentExamEntity> startExam(@PathVariable Long studentId,@PathVariable Long examId){
        StudentExamEntity studentExam=questionNavigationService.startExam(studentId,examId);
        return ResponseEntity.ok(studentExam);
    }

    @GetMapping("/next/{studentExamId}/{currentQuestionId}")
    public ResponseEntity<QuestionDto> getNextQuestion(@PathVariable Long studentExamId, @PathVariable Long currentQuestionId) {
        QuestionDto nextQuestion = questionNavigationService.getNextQuestion(studentExamId, currentQuestionId);
        return ResponseEntity.ok(nextQuestion);
    }

    @GetMapping("/prev/{studentExamId}/{currentQuestionId}")
    public ResponseEntity<QuestionDto> getPreviousQuestion(@PathVariable Long studentExamId, @PathVariable Long currentQuestionId) {
        QuestionDto prevQuestion = questionNavigationService.getPreviousQuestion(studentExamId, currentQuestionId);
        return ResponseEntity.ok(prevQuestion);
    }
}
