package com.example.OnlineExamModule.Controller;

import com.example.OnlineExamModule.DTO.ExamDto;
import com.example.OnlineExamModule.Entity.Exam;
import com.example.OnlineExamModule.Entity.StudentExamEntity;
import com.example.OnlineExamModule.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/exams")
public class ExamController {
    @Autowired
    private ExamService examService;
    @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody ExamDto examDto){
        Exam exam=examService.createExam(examDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(exam);
    }
    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams(){
        List<Exam> exams=examService.getAllExams();
        return ResponseEntity.ok(exams);
    }
    @GetMapping("/{examId}/results")
    public ResponseEntity<List<StudentExamEntity>> getExamResults(@PathVariable Long examId){
        List<StudentExamEntity> results= examService.getExamResults(examId);
        return ResponseEntity.ok(results);
    }
    @PutMapping("/{examId}/passing-criteria")
    public ResponseEntity<Exam> setPassingCriteria(@PathVariable Long examId,@RequestBody int passingCriteria){
        Exam exam =examService.setPassingCriteria(examId,passingCriteria);
        return  ResponseEntity.ok(exam);
    }
}
