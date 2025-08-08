package com.wipro.rk.quizappmonorepo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.rk.quizappmonorepo.entities.Question;
import com.wipro.rk.quizappmonorepo.enums.Category;
import com.wipro.rk.quizappmonorepo.enums.DifficultyLevel;
import com.wipro.rk.quizappmonorepo.repos.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
	
	private final QuestionRepository questionRepository;

	public List<Question> getQuestionsByCategory(Category category) {
		
           return questionRepository.findByCategory(category);

    }

    public Question addQuestion(Question question) {
        
        return questionRepository.save(question);
    }

	public List<Integer> getQuestionsForQuiz(String category, String difficultyLevel) {
		 List<Integer> questions= questionRepository.findRandomQuestionsByCategoryAndLevel(category, difficultyLevel);
		return questions;
	}
}
