package com.andreimironov.homework_1.service.impl;

import com.andreimironov.homework_1.Utils;
import com.andreimironov.homework_1.domain.Question;
import com.andreimironov.homework_1.service.ValidationService;

import java.util.Map;
import java.util.stream.Collectors;

public class ValidationServiceImpl implements ValidationService {
    @Override
    public Map<Question, Boolean> getResult(Map<Question, String> userAnswers) {
        return userAnswers.entrySet()
                          .stream()
                          .collect(Collectors.toMap(Map.Entry::getKey, Utils::validate));
    }
}
