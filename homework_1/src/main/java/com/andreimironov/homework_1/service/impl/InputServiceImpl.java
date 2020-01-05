package com.andreimironov.homework_1.service.impl;

import com.andreimironov.homework_1.domain.Question;
import com.andreimironov.homework_1.domain.UserInfo;
import com.andreimironov.homework_1.service.InputService;
import com.andreimironov.homework_1.service.OutputService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class InputServiceImpl implements InputService {
    private final OutputService outputService;
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public UserInfo getUserInfo() {
        outputService.showInputNameMessage();
        String name = scanner.next();

        outputService.showInputSurNameMessage();
        String surName = scanner.next();

        return UserInfo.builder().name(name).surName(surName).build();
    }

    @Override
    public Map<Question, String> getUserAnswers(List<Question> questions) {
        return questions.stream()
                        .collect(Collectors.toMap(Function.identity(), question -> {
                            outputService.showQuestion(question);
                            return scanner.next();
                        }));
    }

    @Override
    public void onInit() {
        scanner.useDelimiter("\n");
    }

    @Override
    public void onDestroy() {
        scanner.close();
    }
}
