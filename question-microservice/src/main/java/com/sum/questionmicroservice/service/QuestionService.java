package com.sum.questionmicroservice.service;


import com.sum.questionmicroservice.dao.QuestionDao;
import com.sum.questionmicroservice.model.Question;
import com.sum.questionmicroservice.model.QuestionWrapper;
import com.sum.questionmicroservice.model.UserAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {

        try {
            questionDao.save(question);
            return new ResponseEntity<>( "Created", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>( "Failed", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, int numberOfQuestions) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(categoryName, numberOfQuestions);

        return new ResponseEntity<>(questions.stream().map(Question::getId).toList(), HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(List<Integer> questionIds) {
        List<QuestionWrapper> questionWrappers = new ArrayList<>();

        for (int id: questionIds) {
            Optional<Question> question = questionDao.findById(id);

            question.ifPresent(value -> questionWrappers.add(new QuestionWrapper(
                    value.getId(),
                    value.getQuestionTitle(),
                    value.getOption1(),
                    value.getOption2(),
                    value.getOption3(),
                    value.getOption4()
            )));
        }

        return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<UserAnswers> userAnswers) {
        int right = 0;
        for (UserAnswers userAnswer: userAnswers) {
            Optional<Question> question = questionDao.findById(userAnswer.getId());

            if (question.isPresent()) {
                if (userAnswer.getUserAnswer().equals(question.get().getRightAnswer())) {
                    right++;
                }
            }
        }

        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
