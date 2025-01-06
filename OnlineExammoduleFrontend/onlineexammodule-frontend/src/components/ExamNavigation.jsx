import React, { useState, useEffect } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
// import CodeEditor from './CodeEditor';

const ExamNavigation = ({ studentExamId, examId }) => {
    const [question, setQuestion] = useState(null);
    const [currentQuestionId, setCurrentQuestionId] = useState(null);
    const [selectedOption, setSelectedOption] = useState(null);
    const [answerText, setAnswerText] = useState('');

    useEffect(() => {
        fetchQuestion(examId);
    }, [examId]);

    const fetchQuestion = async (examId) => {
        try {
            const response = await axios.get(`http://localhost:8080/api/examNavigation/${examId}/questions`);
            if (response.data.length > 0) {
                setQuestion(response.data[0]);
                setCurrentQuestionId(response.data[0].questionId);
            }
        } catch (error) {
            console.error("Error fetching question:", error);
        }
    };

    const handleNextQuestion = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/examNavigation/next/${studentExamId}/${currentQuestionId}`);
            setQuestion(response.data);
            setCurrentQuestionId(response.data.questionId);
            setSelectedOption(null);
            setAnswerText('');
        } catch (error) {
            console.error("Error fetching next question:", error);
        }
    };

    const handlePreviousQuestion = async () => {
        try {
            const response = await axios.get(`http://localhost:8080/api/examNavigation/prev/${studentExamId}/${currentQuestionId}`);
            setQuestion(response.data);
            setCurrentQuestionId(response.data.questionId);
            setSelectedOption(null);
            setAnswerText('');
        } catch (error) {
            console.error("Error fetching previous question:", error);
        }
    };

    const handleOptionChange = (event) => {
        setSelectedOption(event.target.value);
    };

    const handleAnswerTextChange = (event) => {
        setAnswerText(event.target.value);
    };

    // const handleAnswerTextChange = (text) => {
    //     setAnswerText(text);
    // };


    const handleSubmitAnswer = async () => {
        try {
            await axios.post('http://localhost:8080/api/examNavigation/save-answer', {
                studentExamId,
                questionId: currentQuestionId,
                questionType: question.questionType,
                selectedOption,
                answerText
            });
            alert('Answer saved successfully!');
        } catch (error) {
            console.error("Error saving answer:", error);
        }
    };


    const handleSubmitExam = async () => {
        try {
            await axios.post(`http://localhost:8080/api/examNavigation/submit/${studentExamId}`);
            alert('Exam submitted successfully!');
            // Optionally, you can navigate to a different page or show a success message
        } catch (error) {
            console.error("Error submitting exam:", error);
        }
    };


    if (!question) {
        return <div>Loading...</div>;
    }

    return (
        <div className="container mt-5">
            <div className="d-flex justify-content-between">
                <button className="btn btn-primary" onClick={handlePreviousQuestion} disabled={!currentQuestionId}>
                    Prev
                </button>
                <button className="btn btn-primary" onClick={handleNextQuestion}>
                    Next
                </button>
            </div>
            <div className="mt-4">
                <h4>Question: {question.questionText}</h4>
                {question.questionType === 'MCQ' ? (
                    question.options.map((option, index) => (
                        <div key={index} className="form-check">
                            <input
                                className="form-check-input"
                                type="radio"
                                name="option"
                                value={option}
                                checked={selectedOption === option}
                                onChange={handleOptionChange}
                            />
                            <label className="form-check-label">{option}</label>
                        </div>
                    ))
                ) : (
                    // <CodeEditor value={answerText} onChange={handleAnswerTextChange} />


                    <div className="form-group">
                        <textarea
                            className="form-control"
                            rows="5"
                            value={answerText}
                            onChange={handleAnswerTextChange}
                            placeholder="Enter your answer here..."
                        ></textarea>
                    </div>
                )}
                <button className="btn btn-success mt-3" onClick={handleSubmitAnswer}>
                    Submit Answer
                </button>
                <button className="btn btn-danger mt-3 ms-2" onClick={handleSubmitExam}>
                    Submit Exam
                </button>
            </div>
        </div>
    );
};

export default ExamNavigation;
