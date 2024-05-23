package com.sum.quiz_microservice.controller;

import com.sum.quiz_microservice.model.QuestionWrapper;
import com.sum.quiz_microservice.model.UserAnswers;
import com.sum.quiz_microservice.model.dto.QuizDto;
import com.sum.quiz_microservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto) {
        return quizService.createQuiz(
                quizDto.getCategoryName(),
                quizDto.getNumberOfQuestions(),
                quizDto.getTitle()
        );
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@RequestBody List<UserAnswers> userAnswers) {
        return quizService.calculateResult(userAnswers);
    }
}
