package com.andreimironov.homework_2.service.impl;

import com.andreimironov.homework_2.domain.Question;
import com.andreimironov.homework_2.domain.UserInfo;
import com.andreimironov.homework_2.service.InputService;
import com.andreimironov.homework_2.service.OutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
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

    @PostConstruct
    public void postConstruct() {
        System.out.println("postConstruct");
        scanner.useDelimiter("\n");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("preDestroy");
        scanner.close();
    }
}
