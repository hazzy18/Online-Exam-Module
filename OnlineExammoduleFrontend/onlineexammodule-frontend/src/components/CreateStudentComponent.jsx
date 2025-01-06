import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createStudent } from '../services/StudentService';
import 'bootstrap/dist/css/bootstrap.min.css';


const CreateStudentComponent = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (event) => {
        event.preventDefault();
        const student = {
            name,
            email,
            password
        };
        createStudent(student).then((response) => {
            alert('Student created successfully!');
            navigate('/');
        }).catch((error) => {
            console.error('Error creating student:', error);
        });
    };

    return (
        <div className='container'>
            <h2 className='text-center'>Create a New Student</h2>
            <form onSubmit={handleSubmit}>
                <div className='form-group'>
                    <label>Name</label>
                    <input
                        type='text'
                        className='form-control'
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                </div>
                <div className='form-group'>
                    <label>Email</label>
                    <input
                        type='email'
                        className='form-control'
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </div>
                <div className='form-group'>
                    <label>Password</label>
                    <input
                        type='password'
                        className='form-control'
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </div>
                <button type='submit' className='btn btn-primary'>Create Student</button>
            </form>
        </div>
    );
};

export default CreateStudentComponent;
