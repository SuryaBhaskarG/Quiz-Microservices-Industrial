package com.wipro.rk.quizapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.rk.quizapp.entities.QuestionWrapper;
import com.wipro.rk.quizapp.entities.Quiz;
import com.wipro.rk.quizapp.entities.Response;
import com.wipro.rk.quizapp.feign.QuizInterface;
import com.wipro.rk.quizapp.repos.QuizRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

	private final QuizRepository quizRepository;
	private final QuizInterface quizInterface;

	public Quiz createQuiz(String category, String level, String title) {

		List<Integer> questionIdsForQuiz = quizInterface.getQuestionsForQuiz(category, level);

		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionsIds(questionIdsForQuiz);
		return quizRepository.save(quiz);
	}

	
	public List<QuestionWrapper> getQuizQuestions(Integer id) {
		
		Quiz quiz =quizRepository.findById(id).get();
		
	   List<Integer>	questionIds=quiz.getQuestionsIds();
	   return quizInterface.getQuestionforQuizbyQuizID(questionIds);
	
	}


	public Integer calulateResult(Integer id, List<Response> responses) {
		return quizInterface.getScore(responses);
	}


	public Integer getCountOdQUizs() {
	
		return (int) quizRepository.count();
	}
	
	
	
	

}
