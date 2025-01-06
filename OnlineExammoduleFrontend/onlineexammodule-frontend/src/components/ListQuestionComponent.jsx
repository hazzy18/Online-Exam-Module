import React, { useEffect, useState } from 'react';
import { getQuestions } from '../services/QuestionService';

const ListQuestionComponent = () => {
    const [questions, setQuestions] = useState([]);

    useEffect(() => {
        getQuestions().then((response) => {
            setQuestions(response.data);
        }).catch((error) => {
            console.error('Error fetching questions:', error);
        });
    }, []);

    return (
        <div className='container'>
            <h2 className='text-center'>List of Questions</h2>
            <table className="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Question ID</th>
                        <th>Question Text</th>
                        <th>Category Name</th>
                        <th>Correct Option</th>
                        <th>Difficulty Level</th>
                        <th>Question Type</th>
                    </tr>
                </thead>
                <tbody>
                    {questions.map((question) => (
                        <tr key={question.questionId}>
                            <td>{question.questionId}</td>
                            <td>{question.questionText}</td>
                            <td>{question.categoryName}</td>
                            <td>{question.correctOption}</td>
                            <td>{question.difficultyLevel}</td>
                            <td>{question.questionType}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default ListQuestionComponent;
