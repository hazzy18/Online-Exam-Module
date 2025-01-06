import React from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

const MainPageExamComponent = () => {
    return (
        <div className="container mt-5 text-center">
            <h1 className="mb-4">Exam-Management-Admin</h1>
            <div className="d-flex justify-content-center flex-column">
                <Link to="/create-exam" className="btn btn-primary mb-2 w-50">Create Exam</Link>
                <Link to="/get-all-exams" className="btn btn-primary mb-2 w-50">Get All Exams</Link>
                <Link to="/get-results" className="btn btn-primary w-50">Get Results</Link>
            </div>
        </div>
    );
};

export default MainPageExamComponent;
