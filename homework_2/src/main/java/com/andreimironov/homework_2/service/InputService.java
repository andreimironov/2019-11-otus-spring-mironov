package com.andreimironov.homework_2.service;

import com.andreimironov.homework_2.domain.Question;
import com.andreimironov.homework_2.domain.UserInfo;

import java.util.List;
import java.util.Map;

public interface InputService {
    UserInfo getUserInfo();
    Map<Question, String> getUserAnswers(List<Question> questions);
}
