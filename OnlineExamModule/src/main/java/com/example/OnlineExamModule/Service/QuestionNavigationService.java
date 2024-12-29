package com.example.OnlineExamModule.Service;

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

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class QuestionNavigationService {
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamQuestionRepository examQuestionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private StudentExamRepository studentExamRepository;

    public List<ExamQuestion> getQuestionsForExam(Long examId){
        Exam exam=examRepository.findById(examId)
                .orElseThrow(()->new RuntimeException("Exam Not Found"));
        return examQuestionRepository.findByExam(exam);
    }

    public Question getQuestionById(Long questionId){
        return questionRepository.findById(questionId)
                .orElseThrow(()->new RuntimeException("Question Not found"));
    }

    public StudentExamEntity getStudentExam(Long studentExamId){
       return studentExamRepository.findById(studentExamId)
               .orElseThrow(()->new RuntimeException("Student Exam Not found"));

    }

//    public void submitExam(Long studentExamId, Map<Long,String> answers){
//        StudentExamEntity studentExam=studentExamRepository.findById(studentExamId)
//                .orElseThrow(()->new RuntimeException("StudentExam not found"));
//        int score=calculateScore(studentExam,answers);
//        studentExam.setScore(score);
//        studentExam.setPassed(score>= studentExam.getExam().getPassingCriteria());
//        studentExam.setSubmittedAt(new Date());
//        studentExam.setStatus("SUBMITTED");
//        studentExamRepository.save(studentExam);
//    }
//
//    private int calculateScore(StudentExamEntity studentExam, Map<Long,String> answers){
//        int score=0;
//        List<ExamQuestion>examQuestions= examQuestionRepository.findByExam(studentExam.getExam());
//        for(ExamQuestion examQuestion: examQuestions){
//            Question question=examQuestion.getQuestion();
//            if(question.getCorrectOption().equals(answers.get(question.getQuestionId()))){
//                score++;
//            }
//        }
//        return score;
//    }
public void submitExam(Long studentExamId, Map<Long, String> mcqAnswers, Map<Long, String> programmingAnswers) {
    StudentExamEntity studentExam = studentExamRepository.findById(studentExamId)
            .orElseThrow(() -> new RuntimeException("StudentExam not found"));

    // Calculate score for MCQ questions
    int score = 0;
    List<ExamQuestion> examQuestions = examQuestionRepository.findByExam(studentExam.getExam());
    for (ExamQuestion examQuestion : examQuestions) {
        Question question = examQuestion.getQuestion();
        if ("MCQ".equals(question.getQuestionType()) && question.getCorrectOption().equals(mcqAnswers.get(question.getQuestionId()))) {
            score++;
        }
    }

    studentExam.setMcqAnswers(mcqAnswers);
    studentExam.setProgrammingAnswers(programmingAnswers);
    studentExam.setScore(score);
    studentExam.setPassed(score >= studentExam.getExam().getPassingCriteria());
    studentExam.setSubmittedAt(new Date());
    studentExam.setStatus("SUBMITTED");

    studentExamRepository.save(studentExam);
}

    private int calculateScore(StudentExamEntity studentExam, Map<Long, String> answers) {
        int score = 0;
        List<ExamQuestion> examQuestions = examQuestionRepository.findByExam(studentExam.getExam());
        for (ExamQuestion examQuestion : examQuestions) {
            Question question = examQuestion.getQuestion();
            if (question.getCorrectOption().equals(answers.get(question.getQuestionId()))) {
                score++;
            }
        }
        return score;
    }

    public void saveAnswer(Long studentExamId, Long questionId, String selectedOption) {
        StudentExamEntity studentExam = studentExamRepository.findById(studentExamId)
                .orElseThrow(() -> new RuntimeException("StudentExam Not found"));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question Not found"));

        if ("MCQ".equals(question.getQuestionType())) {
            studentExam.getMcqAnswers().put(questionId, selectedOption);
        } else if ("PROGRAMMING".equals(question.getQuestionType())) {
            studentExam.getProgrammingAnswers().put(questionId, selectedOption);
        }

        studentExamRepository.save(studentExam);
    }
}
