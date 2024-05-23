package com.sum.quiz_microservice.service;


import com.sum.quiz_microservice.dao.QuizDao;
import com.sum.quiz_microservice.feign.QuizInterface;
import com.sum.quiz_microservice.model.QuestionWrapper;
import com.sum.quiz_microservice.model.Quiz;
import com.sum.quiz_microservice.model.UserAnswers;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        if (quiz.isPresent()) {
            List<Integer> quizQuestionIds = quiz.get().getQuestionIds();
            List<QuestionWrapper> questions = quizInterface.getQuestionsFromId(quizQuestionIds).getBody();

            return new ResponseEntity<>(questions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Integer> calculateResult(List<UserAnswers> userAnswers) {
        return quizInterface.getScore(userAnswers);
    }
}
