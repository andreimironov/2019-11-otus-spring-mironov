package com.andreimironov.homework_1.service.impl;

import com.andreimironov.homework_1.domain.Question;
import com.andreimironov.homework_1.domain.UserInfo;
import com.andreimironov.homework_1.service.OutputService;

import java.util.List;
import java.util.Map;

public class OutputServiceImpl implements OutputService {
    @Override
    public void showHelloMessage() {
        System.out.println("Hello, how you doing?");
    }

    @Override
    public void showInputNameMessage() {
        System.out.print("Input your name and type Enter: ");
    }

    @Override
    public void showInputSurNameMessage() {
        System.out.print("Input your surname and type Enter: ");
    }

    @Override
    public void showStartMessage(UserInfo userInfo) {
        System.out
                .println(String.format("OK, %s %s, let's start the test!", userInfo.getName(), userInfo.getSurName()));
    }

    @Override
    public void showQuestion(Question question) {
        showDelimeter();
        System.out.println(String.format("Question %d. %s", question.getId(), question.getQuestion()));
        System.out.print("Input your answer and type Enter: ");
    }

    @Override
    public void showSummation(UserInfo userInfo, List<Question> questions, Map<Question, String> userAnswers, Map<Question, Boolean> result) {
        showDelimeter();
        System.out.println(String.format("Well, %s %s, let's summarize.", userInfo.getName(), userInfo.getSurName()));
        showDelimeter();

        questions.forEach(question -> {
            Integer id = question.getId();
            String wording = question.getQuestion();
            String answer = question.getAnswer();
            String userAnswer = userAnswers.get(question);
            Boolean isRight = result.get(question);
            System.out.println(String.format("Question %d. %s", id, wording));
            System.out.println(String.format("Right answer: %s. Your answer: %s", answer, userAnswer));
            System.out.println(isRight ? "You're right!" : "Oops, mistake(");
            showDelimeter();
        });

        long rightAnswers = result.values().stream().filter(Boolean::booleanValue).count();
        long questionsNumber = questions.size();
        System.out.println(String.format("So your result is %d of %d right answers.", rightAnswers, questionsNumber));

        System.out.println(String.format("Buy, %s %s!", userInfo.getName(), userInfo.getSurName()));
    }

    private void showDelimeter() {
        System.out.println("-----------------------");
    }
}
