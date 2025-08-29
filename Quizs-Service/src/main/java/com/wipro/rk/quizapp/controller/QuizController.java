package com.wipro.rk.quizapp.controller;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.rk.quizapp.entities.QuestionWrapper;
import com.wipro.rk.quizapp.entities.Quiz;
import com.wipro.rk.quizapp.entities.Response;
import com.wipro.rk.quizapp.service.QuizService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/quiz")
public class QuizController {

	private final QuizService quizService;
	


	@CircuitBreaker(name = "createQuiz", fallbackMethod = "fallBackMethodForCreateQuiz")
	@Retry(name = "createQuiz")                          // Retry configured for the same instance "createQuiz"
    @RateLimiter(name = "createQuiz")                    // Rate limiter configured for "createQuiz"
	@PostMapping("/create")
	public Quiz createQuiz(
	        @RequestParam String category,
	        @RequestParam String level,
	        @RequestParam String title) {
	    return quizService.createQuiz(category, level, title);
	}

	public Quiz fallBackMethodForCreateQuiz(String category, String level, String title, Throwable throwable) {
	    log.warn("Fallback triggered for createQuiz: {}", throwable.getMessage());
	    Quiz fallbackQuiz = new Quiz();
	    fallbackQuiz.setTitle("Question server is down please try later - Service Unavailable");
	    return fallbackQuiz;
	}

    @GetMapping("/getQuizById/{id}")
    public  List<QuestionWrapper>  getQuizByID(@PathVariable Integer id)
    {
		return quizService.getQuizQuestions(id);
    	
    }
    @PostMapping("/submitQuiz/{id}")
    public Integer submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses)
    {
		return quizService.calulateResult(id,responses);
    	
    }
    
    @GetMapping("/getNoOFQuizs")
    public Integer getCountOdQUizs()
    {
    	return quizService.getCountOdQUizs();
    }
    
<<<<<<< HEAD
    
    
    
    
    
    
    
    
    /*
    
    @GetMapping("/getQuizByID/{id}")                  
    public List<QuestionWrapper> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }   
       
    @PostMapping("/submitQuiz/{id}")
    public Integer submitQuiz(@PathVariable int id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
    
    
    
    
    
    
    
  
    
    @GetMapping("/getQuizByID/{id}")                  
    public List<QuestionWrapper> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }
    
    
    
    @PostMapping("/submitQuiz/{id}")
    public Integer submitQuiz(@PathVariable int id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
    */
=======
>>>>>>> b70c11e (msg)
}
