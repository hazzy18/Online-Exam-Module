package com.example.OnlineExamModule.Mapper;

import com.example.OnlineExamModule.DTO.QuestionDto;
import com.example.OnlineExamModule.Entity.Option;
import com.example.OnlineExamModule.Entity.Question;
import com.example.OnlineExamModule.Repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {
    @Autowired
    private OptionRepository optionRepository;

    public QuestionDto toDto(Question question){
        QuestionDto questionDto=new QuestionDto();
        questionDto.setQuestionId(question.getQuestionId());
        questionDto.setQuestionText(question.getQuestionText());
        questionDto.setCategoryName(question.getCategory().getCategoryName());
        questionDto.setQuestionType(question.getQuestionType());
        questionDto.setDifficultyLevel(question.getDifficultyLevel());
        questionDto.setReferenceAnswer(question.getReferenceAnswer());
        questionDto.setCorrectOption(question.getCorrectOption());

        if("MCQ".equalsIgnoreCase(question.getQuestionType())){
            List<Option> options=optionRepository.findByQuestion(question);
            questionDto.setOptions(options.stream()
                    .map(Option::getOptionText)
                    .collect(Collectors.toList()));
        }
        return questionDto;

    }

    public Question toEntity(QuestionDto questionDto){
        Question question=new Question();
        question.setQuestionText(questionDto.getQuestionText());
        question.setCorrectOption(questionDto.getCorrectOption());
        question.setReferenceAnswer(questionDto.getReferenceAnswer());
        question.setDifficultyLevel(questionDto.getDifficultyLevel());
        question.setQuestionType(questionDto.getQuestionType());
        return question;
    }

}
