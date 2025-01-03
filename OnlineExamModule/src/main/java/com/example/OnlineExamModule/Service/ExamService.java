package com.example.OnlineExamModule.Service;

import com.example.OnlineExamModule.DTO.ExamDto;
import com.example.OnlineExamModule.Entity.Exam;
import com.example.OnlineExamModule.Entity.ExamQuestion;
import com.example.OnlineExamModule.Entity.Question;
import com.example.OnlineExamModule.Entity.StudentExamEntity;
import com.example.OnlineExamModule.Repository.ExamQuestionRepository;
import com.example.OnlineExamModule.Repository.ExamRepository;
import com.example.OnlineExamModule.Repository.QuestionRepository;
import com.example.OnlineExamModule.Repository.StudentExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    @Autowired
    private StudentExamRepository studentExamRepository;

    public Exam createExam(ExamDto examDto){
        Exam exam=new Exam();
        exam.setExamName((examDto.getExamName()));
        exam.setPassingCriteria(examDto.getPassingCriteria());
        exam.setTotalQuestions(examDto.getTotalQuestions());
        exam.setDuration(examDto.getDuration());
        Exam savedExam=examRepository.save(exam);
        for(Long questionId : examDto.getQuestionIds()){
            Question question=questionRepository.findById(questionId)
                    .orElseThrow(()-> new RuntimeException("Question Not Found"));
            ExamQuestion examQuestion=new ExamQuestion();
            examQuestion.setExam(savedExam);
            examQuestion.setQuestion(question);
            ExamQuestion savedExamQuestion = examQuestionRepository.save(examQuestion);
        }
        return savedExam;
    }

    public List<Exam> getAllExams(){
        return examRepository.findAll();
    }


    public List<StudentExamEntity> getExamResults(Long examId){
        Exam exam=examRepository.findById(examId)
                .orElseThrow(()->new RuntimeException("Exam Not Found"));
        return studentExamRepository.findByExam(exam);
    }
    public Exam setPassingCriteria(Long examId,int passingCriteria){
        Exam exam=examRepository.findById(examId)
                .orElseThrow(()-> new RuntimeException("Exam Not Found"));
        exam.setPassingCriteria(passingCriteria);
        return examRepository.save(exam);
    }

}
