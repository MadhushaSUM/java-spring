package com.sum.quizAppMonolothic.controller;

import com.sum.quizAppMonolothic.model.Question;
import com.sum.quizAppMonolothic.model.QuestionWrapper;
import com.sum.quizAppMonolothic.model.Quiz;
import com.sum.quizAppMonolothic.model.UserAnswers;
import com.sum.quizAppMonolothic.service.QuizService;
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
    public ResponseEntity<String> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title) {
        return quizService.createQuiz(category, numQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id) {
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<UserAnswers> userAnswers) {
        return quizService.calculateResult(id, userAnswers);
    }
}
