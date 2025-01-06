import React, { useEffect, useState } from 'react';
import { getExamResults } from '../services/ExamService';
import 'bootstrap/dist/css/bootstrap.min.css';

const ExamResultsComponent = () => {
    const [examId, setExamId] = useState('');
    const [results, setResults] = useState([]);

    const handleSubmit = (event) => {
        event.preventDefault();
        getExamResults(examId).then((response) => {
            setResults(response.data);
        }).catch((error) => {
            console.error('Error fetching results:', error);
        });
    };

    return (
        <div className="container mt-5">
            <h2 className="text-center mb-4">Get Exam Results</h2>
            <form onSubmit={handleSubmit} className="w-50 mx-auto mb-4">
                <div className="mb-3">
                    <label htmlFor="examId" className="form-label">Exam ID</label>
                    <input
                        type="number"
                        className="form-control"
                        id="examId"
                        value={examId}
                        onChange={(e) => setExamId(e.target.value)}
                        required
                    />
                </div>
                <button type="submit" className="btn btn-primary">Get Results</button>
            </form>
            {results.length > 0 && (
                <table className="table table-striped table-bordered">
                    <thead>
                        <tr>
                            <th>Student ID</th>
                            <th>Student Name</th>
                            <th>Score</th>
                            <th>Passed</th>
                            <th>Submitted At</th>
                        </tr>
                    </thead>
                    <tbody>
                        {results.map((result) => (
                            <tr key={result.studentExamId}>
                                <td>{result.student.studentId}</td>
                                <td>{result.student.name}</td>
                                <td>{result.score}</td>
                                <td>{result.passed ? 'Yes' : 'No'}</td>
                                <td>{new Date(result.submittedAt).toLocaleString()}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
};

export default ExamResultsComponent;
