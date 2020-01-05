package com.andreimironov.homework_1.service;

import com.andreimironov.homework_1.domain.Question;

import java.util.Map;

public interface ValidationService {
    Map<Question, Boolean> getResult(Map<Question, String> userAnswers);
}
