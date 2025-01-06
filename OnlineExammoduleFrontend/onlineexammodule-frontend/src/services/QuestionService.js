import axios from 'axios';

const API_URL = 'http://localhost:8080/api/questions';

export const addQuestion = (questionDto) => {
    return axios.post(API_URL, questionDto);
};

export const getQuestions = () => {
    return axios.get(API_URL);
};
