//package com.example.OnlineExamModule.Scheduler;
//
//import com.example.OnlineExamModule.Entity.StudentExamEntity;
//import com.example.OnlineExamModule.Repository.StudentExamRepository;
//import com.example.OnlineExamModule.Service.QuestionNavigationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//@Component
////enable it to run scheduled tasks when application starts
//public class ExamScheduler {
//@Autowired
//    private StudentExamRepository studentExamRepository;
//@Autowired
//    private QuestionNavigationService questionNavigationService;
//
//    @Scheduled(fixedRate = 60000)
//    public void autoSubmitExams(){
//        List<StudentExamEntity> inProgressExams=studentExamRepository.findByStatus("IN_PROGRESS");
//        Date currentTime = new Date();
//
//        for(StudentExamEntity studentExam : inProgressExams){
//            long examDuration=studentExam.getExam().getDuration();
//            Date startTime=studentExam.getCreatedAt();
//            Date endTime=new Date(startTime.getTime()+examDuration*60000);
//            if(currentTime.after(endTime)){
//                //autosubmit the exam with saved answers
//                Map<Long, String> mcqAnswers = studentExam.getMcqAnswers();
//                Map<Long, String> programmingAnswers = studentExam.getProgrammingAnswers();
//                questionNavigationService.submitExam(studentExam.getStudentExamId(), mcqAnswers, programmingAnswers);
//            }
//        }
//    }
//}
