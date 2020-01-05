package com.andreimironov.homework_1.service;

import com.andreimironov.homework_1.domain.Question;
import com.andreimironov.homework_1.domain.UserInfo;

import java.util.List;
import java.util.Map;

public interface InputService {
    UserInfo getUserInfo();
    Map<Question, String> getUserAnswers(List<Question> questions);
    void onInit();
    void onDestroy();
}
