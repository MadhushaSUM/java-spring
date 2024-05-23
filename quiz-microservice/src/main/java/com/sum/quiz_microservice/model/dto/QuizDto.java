package com.sum.quiz_microservice.model.dto;

import lombok.Data;

@Data
public class QuizDto {
    String categoryName;
    Integer numberOfQuestions;
    String title;
}
