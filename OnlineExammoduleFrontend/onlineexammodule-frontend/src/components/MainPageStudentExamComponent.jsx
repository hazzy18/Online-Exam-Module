import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getAllExams } from '../services/ExamService';
import 'bootstrap/dist/css/bootstrap.min.css';

const MainPageStudentExamComponent = () => {
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
            <h2 className="text-center mb-4">Available Exams</h2>
            <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Exam Name</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {exams.map((exam) => (
                        <tr key={exam.examId}>
                            <td>{exam.examName}</td>
                            <td>
                                <Link to={`/start-exam/${exam.examId}`} className="btn btn-primary">Start Exam</Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default MainPageStudentExamComponent;
