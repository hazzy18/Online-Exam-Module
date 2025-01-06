import axios from 'axios';

const API_URL = 'http://localhost:8080/api/exams';

export const createExam = (examDto) => {
    return axios.post(API_URL, examDto);
};

export const getAllExams = () => {
    return axios.get(API_URL);
};

export const getExamResults = (examId) => {
    return axios.get(`${API_URL}/${examId}/results`);
};

export const setPassingCriteria = (examId, passingCriteria) => {
    return axios.put(`${API_URL}/${examId}/passing-criteria`, passingCriteria);
};

export const getQuestionsByCategory = (categoryName) => {
    return axios.get(`http://localhost:8080/api/questions/category/${categoryName}`);
};
