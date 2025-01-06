import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { createExam, getQuestionsByCategory } from '../services/ExamService';
import 'bootstrap/dist/css/bootstrap.min.css';

const CreateExamComponent = () => {
    const [examName, setExamName] = useState('');
    const [totalQuestions, setTotalQuestions] = useState('');
    const [passingCriteria, setPassingCriteria] = useState('');
    const [duration, setDuration] = useState('');
    const [selectedCategory, setSelectedCategory] = useState('');
    const [questions, setQuestions] = useState([]);
    const [selectedQuestions, setSelectedQuestions] = useState([]);
    const navigate = useNavigate();

    const handleCategoryChange = (event) => {
        setSelectedCategory(event.target.value);
    };

    useEffect(() => {
        if (selectedCategory) {
            getQuestionsByCategory(selectedCategory).then((response) => {
                setQuestions(response.data);
            }).catch((error) => {
                console.error('Error fetching questions:', error);
            });
        }
    }, [selectedCategory]);

    const handleQuestionSelect = (questionId) => {
        if (selectedQuestions.includes(questionId)) {
            setSelectedQuestions(selectedQuestions.filter(id => id !== questionId));
        } else {
            setSelectedQuestions([...selectedQuestions, questionId]);
        }
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        const examDto = {
            examName,
            totalQuestions: parseInt(totalQuestions),
            passingCriteria: parseInt(passingCriteria),
            duration: parseInt(duration),
            questionIds: selectedQuestions
        };
        createExam(examDto).then((response) => {
            alert('Exam created successfully!');
            navigate('/exam-management');
        }).catch((error) => {
            console.error('Error creating exam:', error);
        });
    };

    return (
        <div className="container mt-5">
            <h2 className="text-center mb-4">Create a New Exam</h2>
            <form onSubmit={handleSubmit} className="w-50 mx-auto">
                <div className="mb-3">
                    <label htmlFor="examName" className="form-label">Exam Name</label>
                    <input
                        type="text"
                        className="form-control"
                        id="examName"
                        value={examName}
                        onChange={(e) => setExamName(e.target.value)}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="totalQuestions" className="form-label">Total Questions</label>
                    <input
                        type="number"
                        className="form-control"
                        id="totalQuestions"
                        value={totalQuestions}
                        onChange={(e) => setTotalQuestions(e.target.value)}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="passingCriteria" className="form-label">Passing Criteria</label>
                    <input
                        type="number"
                        className="form-control"
                        id="passingCriteria"
                        value={passingCriteria}
                        onChange={(e) => setPassingCriteria(e.target.value)}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="duration" className="form-label">Duration (in minutes)</label>
                    <input
                        type="number"
                        className="form-control"
                        id="duration"
                        value={duration}
                        onChange={(e) => setDuration(e.target.value)}
                        required
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="category" className="form-label">Select Category</label>
                    <select
                        className="form-select"
                        id="category"
                        value={selectedCategory}
                        onChange={handleCategoryChange}
                        required
                    >
                        <option value="">Select Category</option>
                        <option value="Logical">Logical</option>
                        <option value="Technical">Technical</option>
                        <option value="Programming">Programming</option>
                    </select>
                </div>
                {selectedCategory && (
                    <div>
                        {questions.map((question) => (
                            <div key={question.questionId} className="form-check mb-2">
                                <input
                                    className="form-check-input"
                                    type="checkbox"
                                    id={`question-${question.questionId}`}
                                    value={question.questionId}
                                    checked={selectedQuestions.includes(question.questionId)}
                                    onChange={() => handleQuestionSelect(question.questionId)}
                                />
                                <label className="form-check-label" htmlFor={`question-${question.questionId}`}>
                                    {question.questionText}
                                </label>
                            </div>
                        ))}
                    </div>
                )}
                <button type="submit" className="btn btn-primary mt-3">Create Exam</button>
            </form>
        </div>
    );
};

export default CreateExamComponent;
