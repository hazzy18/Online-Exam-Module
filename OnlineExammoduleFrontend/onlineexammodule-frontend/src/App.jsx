import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import { BrowserRouter as Router, Route, Routes, useParams } from 'react-router-dom';
import MainPageStudentComponent from './components/MainPageStudentComponent';
import CreateStudentComponent from './components/CreateStudentComponent';
import ListStudentComponent from './components/ListStudentComponent';
import 'bootstrap/dist/css/bootstrap.min.css';
import MainPageComponent from './components/MainPageComponent';
import MainPageQuestionComponent from './components/MainPageQuestionComponent';
import CreateQuestionComponent from './components/CreateQuestionComponent';
import ListQuestionComponent from './components/ListQuestionComponent';
import MainPageExamComponent from './components/MainPageExamComponent';
import CreateExamComponent from './components/CreateExamComponent';
import ListExamComponent from './components/ListExamComponent';
import ExamResultsComponent from './components/ExamResultsComponent';
import MainPageStudentExamComponent from './components/MainPageStudentExamComponent';
import StartExamComponent from './components/StartExamComponent';
import QuestionNavigationComponent from './components/QuestionNavigationComponent';
import ExamNavigation from './components/ExamNavigation';



const App = () => {
  return (
      <Router>
          <Routes>
            {/* Student-Exam-Giving-Related */}
              <Route path="/" element={<MainPageStudentExamComponent />} />
              <Route path="/start-exam/:examId" element={<StartExamComponent />} />
              <Route path="/exam-navigation/:studentExamId/:examId" element={<ExamNavigationWrapper />} />
            {/* Student-Exam-Giving-Related */}
            <Route path="/admin-main" element={<MainPageComponent />} />
            <Route path="/student-management" element={<MainPageStudentComponent />} />
            <Route path="/create-student" element={<CreateStudentComponent />} />
            <Route path="/get-all-students" element={<ListStudentComponent />} />
            <Route path="/question-management" element={<MainPageQuestionComponent />} />
            <Route path="/create-question" element={<CreateQuestionComponent />} />
            <Route path="/view-questions" element={<ListQuestionComponent />} />
            <Route path="/exam-management" element={<MainPageExamComponent />} />
            <Route path="/create-exam" element={<CreateExamComponent />} />
            <Route path="/get-all-exams" element={<ListExamComponent />} />
            <Route path="/get-results" element={<ExamResultsComponent />} />  



          </Routes>
      </Router>
  );
};

const ExamNavigationWrapper = () => {
  const { studentExamId, examId } = useParams();
  return <ExamNavigation studentExamId={studentExamId} examId={examId} />;
};

export default App;
