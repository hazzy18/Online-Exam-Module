import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getQuestionsForExam, getQuestionById, saveAnswer } from '../services/QuestionNavigationService';
import 'bootstrap/dist/css/bootstrap.min.css';

const QuestionNavigationComponent = () => {
    const { studentExamId } = useParams();
    const [questions, setQuestions] = useState([]);
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0);
    const [selectedOption, setSelectedOption] = useState('');
    const [answerText, setAnswerText] = useState('');
    const [currentQuestion, setCurrentQuestion] = useState(null);

    useEffect(() => {
        getQuestionsForExam(studentExamId).then((response) => {
            setQuestions(response.data);
            setCurrentQuestion(response.data[currentQuestionIndex]);
        }).catch((error) => {
            console.error('Error fetching questions:', error);
        });
    }, [studentExamId, currentQuestionIndex]);

    useEffect(() => {
        if (currentQuestion) {
            getQuestionById(currentQuestion.questionId).then((response) => {
                setCurrentQuestion(response.data);
            }).catch((error) => {
                console.error('Error fetching question:', error);
            });
        }
    }, [currentQuestion]);

    const handleSaveAnswer = () => {
        const answerDetails = {
            studentExamId,
            questionId: currentQuestion.questionId,
            selectedOption,
            answerText,
            questionType: currentQuestion.questionType
        };
        saveAnswer(answerDetails).then(() => {
            alert('Answer saved successfully!');
        }).catch((error) => {
            console.error('Error saving answer:', error);
        });
    };

    const handleNextQuestion = () => {
        if (currentQuestionIndex < questions.length - 1) {
            setCurrentQuestionIndex(currentQuestionIndex + 1);
            setSelectedOption('');
            setAnswerText('');
            setCurrentQuestion(questions[currentQuestionIndex + 1]);
        }
    };

    const handlePrevQuestion = () => {
        if (currentQuestionIndex > 0) {
            setCurrentQuestionIndex(currentQuestionIndex - 1);
            setSelectedOption('');
            setAnswerText('');
            setCurrentQuestion(questions[currentQuestionIndex - 1]);
        }
    };

    return (
        <div className="container mt-5">
            <h2 className="text-center mb-4">Question {currentQuestionIndex + 1}</h2>
            {currentQuestion && (
                <div className="card">
                    <div className="card-body">
                        <h5 className="card-title">{currentQuestion.questionText}</h5>
                        {currentQuestion.questionType === 'MCQ' && (
                            <div>
                                {currentQuestion.options.map((option, index) => (
                                    <div key={index} className="form-check">
                                        <input
                                            className="form-check-input"
                                            type="radio"
                                            name="selectedOption"
                                            id={`option-${index}`}
                                            value={option}
                                            checked={selectedOption === option}
                                            onChange={(e) => setSelectedOption(e.target.value)}
                                        />
                                        <label className="form-check-label" htmlFor={`option-${index}`}>
                                            {option}
                                        </label>
                                    </div>
                                ))}
                            </div>
                        )}
                        {currentQuestion.questionType === 'PROGRAMMING' && (
                            <div>
                                <textarea
                                    className="form-control"
                                    rows="5"
                                    value={answerText}
                                    onChange={(e) => setAnswerText(e.target.value)}
                                ></textarea>
                            </div>
                        )}
                        <button className="btn btn-primary mt-3" onClick={handleSaveAnswer}>Save Answer</button>
                        <div className="d-flex justify-content-between mt-3">
                            <button className="btn btn-secondary" onClick={handlePrevQuestion} disabled={currentQuestionIndex === 0}>Previous</button>
                            <button className="btn btn-primary" onClick={handleNextQuestion} disabled={currentQuestionIndex === questions.length - 1}>Next</button>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default QuestionNavigationComponent;
