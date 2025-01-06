import React, { useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

const StartExamComponent = () => {
    const { examId } = useParams();
    const navigate = useNavigate();
    const [studentId, setStudentId] = useState('');

    const handleStartExam = async () => {
        try {
            const response = await axios.post(`http://localhost:8080/api/examNavigation/start/${studentId}/${examId}`);
            navigate(`/exam-navigation/${response.data.studentExamId}/${examId}`);
        } catch (error) {
            console.error("Error starting exam:", error);
        }
    };

    return (
        <div className="container mt-5">
            <h2 className="text-center mb-4">Start Exam</h2>
            <div className="form-group">
                <label>Student ID</label>
                <input
                    type="text"
                    className="form-control"
                    value={studentId}
                    onChange={(e) => setStudentId(e.target.value)}
                    placeholder="Enter your student ID"
                />
            </div>
            <button className="btn btn-primary mt-3" onClick={handleStartExam}>
                Start Exam
            </button>
        </div>
    );
};

export default StartExamComponent;
