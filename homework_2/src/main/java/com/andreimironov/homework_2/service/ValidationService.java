package com.andreimironov.homework_2.service;

import com.andreimironov.homework_2.domain.Question;

import java.util.Map;

public interface ValidationService {
    Map<Question, Boolean> getResult(Map<Question, String> userAnswers);
}
