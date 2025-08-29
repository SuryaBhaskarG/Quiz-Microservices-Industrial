package com.wipro.rk.quizapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.rk.quizapp.entities.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}
