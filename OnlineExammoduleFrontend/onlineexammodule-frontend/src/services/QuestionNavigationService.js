import axios from 'axios';

const API_URL = 'http://localhost:8080/api/examNavigation';

export const getQuestionsForExam = (examId) => {
    return axios.get(`${API_URL}/${examId}/questions`);
};

export const getQuestionById = (questionId) => {
    return axios.get(`${API_URL}/questions/${questionId}`);
};

export const saveAnswer = (answerDetails) => {
    return axios.post(`${API_URL}/save-answer`, answerDetails);
};

export const startExam = (studentId, examId) => {
    return axios.post(`${API_URL}/start/${studentId}/${examId}`);
};
