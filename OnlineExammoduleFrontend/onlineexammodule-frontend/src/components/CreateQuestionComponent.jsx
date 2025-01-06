import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { addQuestion } from '../services/QuestionService';

const CreateQuestionComponent = () => {
    const [questionText, setQuestionText] = useState('');
    const [categoryName, setCategoryName] = useState('');
    const [correctOption, setCorrectOption] = useState('');
    const [referenceAnswer, setReferenceAnswer] = useState('');
    const [difficultyLevel, setDifficultyLevel] = useState('');
    const [questionType, setQuestionType] = useState('');
    const [options, setOptions] = useState(['', '']);
    const navigate = useNavigate();

    const handleAddOption = () => {
        if (options.length < 4) {
            setOptions([...options, '']);
        }
    };

    const handleRemoveOption = (index) => {
        if (options.length > 2) {
            const newOptions = options.filter((_, i) => i !== index);
            setOptions(newOptions);
        }
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        const questionDto = {
            questionText,
            categoryName,
            correctOption,
            referenceAnswer,
            difficultyLevel,
            questionType,
            options
        };
        addQuestion(questionDto).then((response) => {
            alert('Question added successfully!');
            navigate('/');
        }).catch((error) => {
            console.error('Error adding question:', error);
        });
    };

    return (
        <div className='container'>
            <h2 className='text-center'>Create a New Question</h2>
            <form onSubmit={handleSubmit}>
                <div className='form-group'>
                    <label>Question Text</label>
                    <input
                        type='text'
                        className='form-control'
                        value={questionText}
                        onChange={(e) => setQuestionText(e.target.value)}
                        required
                    />
                </div>
                <div className='form-group'>
                    <label>Category Name</label>
                    <input
                        type='text'
                        className='form-control'
                        value={categoryName}
                        onChange={(e) => setCategoryName(e.target.value)}
                        required
                    />
                </div>
                <div className='form-group'>
                    <label>Difficulty Level</label>
                    <input
                        type='text'
                        className='form-control'
                        value={difficultyLevel}
                        onChange={(e) => setDifficultyLevel(e.target.value)}
                        required
                    />
                </div>
                <div className='form-group'>
                    <label>Question Type</label>
                    <select
                        className='form-control'
                        value={questionType}
                        onChange={(e) => setQuestionType(e.target.value)}
                        required
                    >
                        <option value="">Select Question Type</option>
                        <option value="MCQ">MCQ</option>
                        <option value="PROGRAMMING">Programming</option>
                    </select>
                </div>
                {questionType === 'MCQ' && (
                    <>
                        <div className='form-group'>
                            <label>Correct Option</label>
                            <input
                                type='text'
                                className='form-control'
                                value={correctOption}
                                onChange={(e) => setCorrectOption(e.target.value)}
                                required
                            />
                        </div>
                        {options.map((option, index) => (
                            <div key={index} className='form-group'>
                                <label>Option {index + 1}</label>
                                <input
                                    type='text'
                                    className='form-control'
                                    value={option}
                                    onChange={(e) => {
                                        const newOptions = [...options];
                                        newOptions[index] = e.target.value;
                                        setOptions(newOptions);
                                    }}
                                    required
                                />
                                <button
                                    type='button'
                                    className='btn btn-danger mt-2'
                                    onClick={() => handleRemoveOption(index)}
                                >
                                    Remove
                                </button>
                            </div>
                        ))}
                        <button
                            type='button'
                            className='btn btn-success mt-2'
                            onClick={handleAddOption}
                        >
                            Add Option
                        </button>
                    </>
                )}
                {questionType === 'PROGRAMMING' && (
                    <div className='form-group'>
                        <label>Reference Answer</label>
                        <input
                            type='text'
                            className='form-control'
                            value={referenceAnswer}
                            onChange={(e) => setReferenceAnswer(e.target.value)}
                            required
                        />
                    </div>
                )}
                <button type='submit' className='btn btn-primary mt-3'>Add Question</button>
            </form>
        </div>
    );
};

export default CreateQuestionComponent;
