package com.sum.quizAppMonolothic.service;

import com.sum.quizAppMonolothic.dao.QuestionDao;
import com.sum.quizAppMonolothic.dao.QuizDao;
import com.sum.quizAppMonolothic.model.Question;
import com.sum.quizAppMonolothic.model.QuestionWrapper;
import com.sum.quizAppMonolothic.model.Quiz;
import com.sum.quizAppMonolothic.model.UserAnswers;
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
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDao.findById(id);
        if (quiz.isPresent()) {
            List<Question> questionsFromDB = quiz.get().getQuestions();
            List<QuestionWrapper> questionsForUser = new ArrayList<>();

            for (Question q : questionsFromDB) {
                questionsForUser.add(new QuestionWrapper(
                        q.getId(),
                        q.getQuestionTitle(),
                        q.getOption1(),
                        q.getOption2(),
                        q.getOption3(),
                        q.getOption4()
                ));
            }

            return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Integer> calculateResult(int id, List<UserAnswers> userAnswers) {
        Optional<Quiz> quiz = quizDao.findById(id);

        if (quiz.isPresent()) {
            List<Question> questions = quiz.get().getQuestions();

            int right = 0;
            int i = 0;
            for (UserAnswers userAnswer: userAnswers) {
                if (userAnswer.getUserAnswer().equals(questions.get(i).getRightAnswer())) {
                    right++;
                }
                i++;
            }

            return new ResponseEntity<>(right, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
