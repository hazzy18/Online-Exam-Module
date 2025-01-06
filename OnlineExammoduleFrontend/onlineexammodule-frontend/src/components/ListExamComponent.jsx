import React, { useEffect, useState } from 'react';
import { getAllExams } from '../services/ExamService';
import 'bootstrap/dist/css/bootstrap.min.css';

const ListExamComponent = () => {
    const [exams, setExams] = useState([]);

    useEffect(() => {
        getAllExams().then((response) => {
            setExams(response.data);
        }).catch((error) => {
            console.error('Error fetching exams:', error);
        });
    }, []);

    return (
        <div className="container mt-5">
            <h2 className="text-center mb-4">List of Exams</h2>
            <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Exam ID</th>
                        <th>Exam Name</th>
                        <th>Total Questions</th>
                        <th>Passing Criteria</th>
                        <th>Duration</th>
                        <th>Created At</th>
                    </tr>
                </thead>
                <tbody>
                    {exams.map((exam) => (
                        <tr key={exam.examId}>
                            <td>{exam.examId}</td>
                            <td>{exam.examName}</td>
                            <td>{exam.totalQuestions}</td>
                            <td>{exam.passingCriteria}</td>
                            <td>{exam.duration}</td>
                            <td>{new Date(exam.createdAt).toLocaleString()}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListExamComponent;
