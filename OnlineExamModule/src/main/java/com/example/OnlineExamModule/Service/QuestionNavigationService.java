package com.example.OnlineExamModule.Service;

import com.example.OnlineExamModule.DTO.AnswerDetailsDto;
import com.example.OnlineExamModule.Entity.*;
import com.example.OnlineExamModule.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private ProgrammingAnswerRepository programmingAnswerRepository;

    @Autowired
    private McqAnswerRepository mcqAnswerRepository;


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


    //-----------------------------------------------------------------//
    //-------------latest submit exam: --------------------------------//
//public void submitExam(Long studentExamId, Map<Long, String> mcqAnswers, Map<Long, String> programmingAnswers) {
//    StudentExamEntity studentExam = studentExamRepository.findById(studentExamId)
//            .orElseThrow(() -> new RuntimeException("StudentExam not found"));
//
//    // Calculate score for MCQ questions
//    int score = 0;
//    List<ExamQuestion> examQuestions = examQuestionRepository.findByExam(studentExam.getExam());
//    for (ExamQuestion examQuestion : examQuestions) {
//        Question question = examQuestion.getQuestion();
//        if ("MCQ".equals(question.getQuestionType()) && question.getCorrectOption().equals(mcqAnswers.get(question.getQuestionId()))) {
//            score++;
//        }
//    }
//
//    studentExam.setMcqAnswers(mcqAnswers);
//    studentExam.setProgrammingAnswers(programmingAnswers);
//    studentExam.setScore(score);
//    studentExam.setPassed(score >= studentExam.getExam().getPassingCriteria());
//    studentExam.setSubmittedAt(new Date());
//    studentExam.setStatus("SUBMITTED");
//
//    studentExamRepository.save(studentExam);
//}

    public void submitExam(Long studentExamId) {
        StudentExamEntity studentExam = studentExamRepository.findById(studentExamId)
                .orElseThrow(() -> new RuntimeException("StudentExam not found"));

        // Fetch MCQ answers
        List<McqAnswerEntity> mcqAnswers = mcqAnswerRepository.findByStudentExam(studentExam);

        // Fetch Programming answers
        List<ProgrammingAnswerEntity> programmingAnswers = programmingAnswerRepository.findByStudentExam(studentExam);

        // Calculate score for MCQ questions
        int score = 0;
        List<ExamQuestion> examQuestions = examQuestionRepository.findByExam(studentExam.getExam());
        for (ExamQuestion examQuestion : examQuestions) {
            Question question = examQuestion.getQuestion();
            if ("MCQ".equals(question.getQuestionType())) {
                McqAnswerEntity answer = mcqAnswers.stream()
                        .filter(a -> a.getQuestionId().equals(question.getQuestionId()))
                        .findFirst()
                        .orElse(null);
                if (answer != null && question.getCorrectOption().equals(answer.getSelectedOption())) {
                    score++;
                }
            }
        }

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

//    public void saveAnswer(AnswerDetailsDto answerDetails) {
//        StudentExamEntity studentExam = studentExamRepository.findById(answerDetails.getStudentExamId())
//                .orElseThrow(() -> new RuntimeException("StudentExam Not found"));
//        Question question = questionRepository.findById(answerDetails.getQuestionId())
//                .orElseThrow(() -> new RuntimeException("Question Not found"));
//
//        if ("MCQ".equals(answerDetails.getQuestionType())) {
//            McqAnswerEntity mcqAnswer = new McqAnswerEntity();
//            mcqAnswer.setStudentExam(studentExam);
//            mcqAnswer.setQuestionId(answerDetails.getQuestionId());
//            mcqAnswer.setSelectedOption(answerDetails.getSelectedOption());
//            studentExam.getMcqAnswers().add(mcqAnswer);
//        } else if ("PROGRAMMING".equals(answerDetails.getQuestionType())) {
//            ProgrammingAnswerEntity programmingAnswer = new ProgrammingAnswerEntity();
//            programmingAnswer.setStudentExam(studentExam);
//            programmingAnswer.setQuestionId(answerDetails.getQuestionId());
//            programmingAnswer.setAnswerText(answerDetails.getAnswerText());
//            studentExam.getProgrammingAnswers().add(programmingAnswer);
//        }
//
//        studentExamRepository.save(studentExam);
//    }



   // ----------------------------------------------------------------------------------------------------------------------------------
    //                            current save api without cascading: -------------------------------------------------

    public void saveAnswer(AnswerDetailsDto answerDetails) {
        StudentExamEntity studentExam = studentExamRepository.findById(answerDetails.getStudentExamId())
                .orElseThrow(() -> new RuntimeException("StudentExam Not found"));
        Question question = questionRepository.findById(answerDetails.getQuestionId())
                .orElseThrow(() -> new RuntimeException("Question Not found"));

        if ("MCQ".equals(answerDetails.getQuestionType())) {
            McqAnswerEntity mcqAnswer = new McqAnswerEntity();
            mcqAnswer.setStudentExam(studentExam);
            mcqAnswer.setQuestionId(answerDetails.getQuestionId());
            mcqAnswer.setSelectedOption(answerDetails.getSelectedOption());
            mcqAnswerRepository.save(mcqAnswer);
        } else if ("PROGRAMMING".equals(answerDetails.getQuestionType())) {
            ProgrammingAnswerEntity programmingAnswer = new ProgrammingAnswerEntity();
            programmingAnswer.setStudentExam(studentExam);
            programmingAnswer.setQuestionId(answerDetails.getQuestionId());
            programmingAnswer.setAnswerText(answerDetails.getAnswerText());
            programmingAnswerRepository.save(programmingAnswer);
        }
    }







    public StudentExamEntity startExam(Long studentId,Long examId){
        StudentProfileEntity student=studentProfileRepository.findById(studentId)
                .orElseThrow(()->new RuntimeException("Student Not Found"));
        Exam exam=examRepository.findById(examId)
                .orElseThrow(()->new RuntimeException("Exam Not Found"));
        StudentExamEntity studentExamEntity=new StudentExamEntity();
        studentExamEntity.setStudent(student);
        studentExamEntity.setStatus("IN_PROGRESS");
        studentExamEntity.setScore(0);
        studentExamEntity.setExam(exam);
        studentExamEntity.setCreatedAt(new Date());
        studentExamEntity.setPassed(false);
        return studentExamRepository.save(studentExamEntity);

    }

}
