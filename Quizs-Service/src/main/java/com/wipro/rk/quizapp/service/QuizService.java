package com.wipro.rk.quizapp.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.wipro.rk.quizapp.entities.Quiz;
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

}
