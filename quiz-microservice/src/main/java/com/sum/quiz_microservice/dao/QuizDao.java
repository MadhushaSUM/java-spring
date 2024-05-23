package com.sum.quiz_microservice.dao;

import com.sum.quiz_microservice.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
