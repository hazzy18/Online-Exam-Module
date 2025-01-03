package com.example.OnlineExamModule.Service;

import com.example.OnlineExamModule.DTO.QuestionDto;
import com.example.OnlineExamModule.Entity.Category;
import com.example.OnlineExamModule.Entity.Option;
import com.example.OnlineExamModule.Entity.Question;
import com.example.OnlineExamModule.Mapper.QuestionMapper;
import com.example.OnlineExamModule.Repository.CategoryRepository;
import com.example.OnlineExamModule.Repository.OptionRepository;
import com.example.OnlineExamModule.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private QuestionMapper questionMapper;

//    public Question createQuestion(QuestionDto questionDto) {
//        // Retrieve or create category
//        Category category = categoryRepository.findByCategoryName(questionDto.getCategoryName())
//                .orElseThrow(() -> new RuntimeException("Category not found"));
//
//        // Create Question entity
//        Question question = new Question();
//        question.setQuestionText(questionDto.getQuestionText());
//        question.setCategory(category);
//        question.setCorrectOption(questionDto.getCorrectOption());
//        question.setReferenceAnswer(questionDto.getReferenceAnswer());
//        question.setDifficultyLevel(questionDto.getDifficultyLevel());
//        question.setQuestionType(questionDto.getQuestionType());
//
//        // Save Question
//        Question savedQuestion = questionRepository.save(question);
//
//        // Map and Save Options for MCQ questions
//        if ("MCQ".equalsIgnoreCase(questionDto.getQuestionType())) {
////            int count = 1; // Initialize a counter outside the loop
//            for (String optionText : questionDto.getOptions()) {
//                Option option = new Option();
////                option.setOptionLabel("Option " + count++); // Dynamic label
//                option.setOptionText(optionText);
//                option.setQuestion(savedQuestion);
//
//                optionRepository.save(option);
//            }
//        }
//
//        return savedQuestion;
//    }



/// //////-------------------------------------------------------------------------------------------------
/// ---------------------------New Create Question for UI-------------------------------------------------



public Question createQuestion(QuestionDto questionDto) {
    // Retrieve or create category
    Category category = categoryRepository.findByCategoryName(questionDto.getCategoryName())
            .orElseThrow(() -> new RuntimeException("Category not found"));

    // Create Question entity
    Question question = new Question();
    question.setQuestionText(questionDto.getQuestionText());
    question.setCategory(category);
    question.setDifficultyLevel(questionDto.getDifficultyLevel());
    question.setQuestionType(questionDto.getQuestionType());

    if ("MCQ".equalsIgnoreCase(questionDto.getQuestionType())) {
        question.setCorrectOption(questionDto.getCorrectOption());
        // Save Question
        Question savedQuestion = questionRepository.save(question);
        // Map and Save Options for MCQ questions
        for (String optionText : questionDto.getOptions()) {
            Option option = new Option();
            option.setOptionText(optionText);
            option.setQuestion(savedQuestion);
            optionRepository.save(option);
        }
    } else if ("PROGRAMMING".equalsIgnoreCase(questionDto.getQuestionType())) {
        question.setReferenceAnswer(questionDto.getReferenceAnswer());
        // Save Question
        questionRepository.save(question);
    }

    return question;
}











    public List<QuestionDto> getQuestionsByCategory(String categoryName) {
        Category category = categoryRepository.findByCategoryName(categoryName)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        List<Question> questions=questionRepository.findByCategory(category);
        return questions.stream()
                .map(questionMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<QuestionDto> getQuestionsByDifficulty(String difficultyLevel) {
        List<Question> questions=questionRepository.findByDifficultyLevel(difficultyLevel);

        return questions.stream()
                .map(questionMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<QuestionDto> getQuestionsByType(String questionType) {
        List<Question> questions=questionRepository.findByQuestionType(questionType);

        return questions.stream()
                .map(questionMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<QuestionDto> getAllQuestions() {
        List<Question> questions = questionRepository.findAll();
        return questions.stream()
                .map(questionMapper::toDto)
                .collect(Collectors.toList());
    }

}
