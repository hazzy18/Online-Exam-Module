import React from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

const MainPageComponent = () => {
    return (
        <div className="container mt-5 text-center">
            <h1 className="mb-4">Admin</h1>
            <div className="d-flex justify-content-center flex-column">
                <Link to="/student-management" className="btn btn-primary mb-2 w-50">Student-Profile Management - Admin</Link>
                <Link to="/question-management" className="btn btn-primary mb-2 w-50">Question-Management-Admin</Link>
                <Link to="/exam-management" className="btn btn-primary w-50">Exam-Management-Admin</Link>
            </div>
        </div>
    );
};

export default MainPageComponent;
