package com.wipro.rk.quizapp.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="QUESTION-SERVICE",path="/api/v1/question")
public interface QuizInterface {

	
    @GetMapping("/generateQuestionsForQuiz")
    public List<Integer> getQuestionsForQuiz(@RequestParam String category,@RequestParam String  difficultyLevel);
    
   
	
}
