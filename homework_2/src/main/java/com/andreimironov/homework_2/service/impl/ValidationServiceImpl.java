package com.andreimironov.homework_2.service.impl;

import com.andreimironov.homework_2.Utils;
import com.andreimironov.homework_2.domain.Question;
import com.andreimironov.homework_2.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public Map<Question, Boolean> getResult(Map<Question, String> userAnswers) {
        return userAnswers.entrySet()
                          .stream()
                          .collect(Collectors.toMap(Map.Entry::getKey, Utils::validate));
    }
}
