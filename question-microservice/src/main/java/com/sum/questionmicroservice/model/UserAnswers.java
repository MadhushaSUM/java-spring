package com.sum.questionmicroservice.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserAnswers {
    private int id;
    private String userAnswer;
}
