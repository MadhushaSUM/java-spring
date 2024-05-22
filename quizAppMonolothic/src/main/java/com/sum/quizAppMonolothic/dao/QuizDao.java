package com.sum.quizAppMonolothic.dao;

import com.sum.quizAppMonolothic.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDao extends JpaRepository<Quiz, Integer> {
}
