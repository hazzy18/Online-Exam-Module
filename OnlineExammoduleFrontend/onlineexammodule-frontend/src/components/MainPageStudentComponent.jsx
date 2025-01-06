import React from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';

const MainPageStudentComponent = () => {
    return (
        <div className='container'>
            <h1 className='text-center'>Student-Profile-Management-Admin</h1>
            <div className='d-flex justify-content-center mt-4'>
                <Link to="/create-student" className='btn btn-primary mx-2'>Create Student</Link>
                <Link to="/get-all-students" className='btn btn-secondary mx-2'>Get All Students</Link>
            </div>
        </div>
    );
};

export default MainPageStudentComponent;
