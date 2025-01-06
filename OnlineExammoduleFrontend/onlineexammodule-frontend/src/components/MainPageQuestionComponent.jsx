import React from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

const MainPageQuestionComponent = () => {
    return (
        <div className="container mt-5">
            <h1 className="text-center mb-4">Question-Management-Admin</h1>
            <div className="d-flex justify-content-center">
                <Link to="/create-question" className="btn btn-primary mx-2">Create Question</Link>
                <Link to="/view-questions" className="btn btn-primary mx-2">View All Questions</Link>
            </div>
        </div>
    );
};

export default MainPageQuestionComponent;
