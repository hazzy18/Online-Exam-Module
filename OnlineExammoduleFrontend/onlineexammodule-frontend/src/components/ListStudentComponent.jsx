import React, { useEffect, useState } from 'react';
import { getAllStudents } from '../services/StudentService';
import 'bootstrap/dist/css/bootstrap.min.css';

const ListStudentComponent = () => {
    const [students, setStudents] = useState([]);

    useEffect(() => {
        getAllStudents().then((response) => {
            setStudents(response.data);
        }).catch((error) => {
            console.error('Error fetching students:', error);
        });
    }, []);

    return (
        <div className='container'>
            <h2 className='text-center'>List of Students</h2>
            <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Student ID</th>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Created At</th>
                    </tr>
                </thead>
                <tbody>
                    {students.map((student) => (
                        <tr key={student.studentId}>
                            <td>{student.studentId}</td>
                            <td>{student.name}</td>
                            <td>{student.email}</td>
                            <td>{new Date(student.createdAt).toLocaleString()}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListStudentComponent;
