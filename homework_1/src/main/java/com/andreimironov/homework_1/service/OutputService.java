package com.andreimironov.homework_1.service;

import com.andreimironov.homework_1.domain.Question;
import com.andreimironov.homework_1.domain.UserInfo;

import java.util.List;
import java.util.Map;

public interface OutputService {
    void showHelloMessage();
    void showInputNameMessage();
    void showInputSurNameMessage();
    void showStartMessage(UserInfo userInfo);
    void showQuestion(Question question);
    void showSummation(UserInfo userInfo, List<Question> questions, Map<Question, String> userAnswers, Map<Question, Boolean> result);
}
