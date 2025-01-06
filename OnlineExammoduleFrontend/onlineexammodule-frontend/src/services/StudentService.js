import axios from 'axios';

const API_URL = 'http://localhost:8080/api/students';

export const createStudent = (student) => {
    return axios.post(API_URL, student);
};

export const getAllStudents = () => {
    return axios.get(API_URL);
};
