package com.andreimironov.homework_2.service.impl;

import com.andreimironov.homework_2.bean.PropertiesHolder;
import com.andreimironov.homework_2.domain.Question;
import com.andreimironov.homework_2.domain.UserInfo;
import com.andreimironov.homework_2.service.OutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OutputServiceImpl implements OutputService {
    private final PropertiesHolder propertiesHolder;
    private final MessageSource messageSource;

    @Override
    public void showHelloMessage() {
        String helloMessage = messageSource.getMessage("hello", null, propertiesHolder.getCurrentLocale());
        System.out.println(helloMessage);
    }

    @Override
    public void showInputNameMessage() {
        String callToInputNameMessage = messageSource.getMessage("callToInput.name", null, propertiesHolder.getCurrentLocale());
        System.out.print(callToInputNameMessage);
    }

    @Override
    public void showInputSurNameMessage() {
        String callToInputSurnameMessage = messageSource.getMessage("callToInput.surname", null, propertiesHolder.getCurrentLocale());
        System.out.print(callToInputSurnameMessage);
    }

    @Override
    public void showStartMessage(UserInfo userInfo) {
        String startMessage = messageSource
                .getMessage("start", new Object[]{userInfo.getName(), userInfo.getSurName()}, propertiesHolder.getCurrentLocale());
        System.out.println(startMessage);
    }

    @Override
    public void showQuestion(Question question) {
        showDelimeter();

        String questionWordingMessage = messageSource.getMessage("questionWording", new Object[]{question.getId(),
                question.getQuestion()}, propertiesHolder.getCurrentLocale());
        System.out.println(questionWordingMessage);

        String callToInputAnswerMessage = messageSource.getMessage("callToInput.answer", new Object[]{question.getId(),
                question.getQuestion()}, propertiesHolder.getCurrentLocale());
        System.out.print(callToInputAnswerMessage);
    }

    @Override
    public void showSummation(UserInfo userInfo, List<Question> questions, Map<Question, String> userAnswers,
                              Map<Question, Boolean> result) {
        showDelimeter();

        String startSummationMessage = messageSource.getMessage("startSummation", new Object[]{userInfo.getName(), userInfo.getSurName()}
                , propertiesHolder.getCurrentLocale());
        System.out.println(startSummationMessage);

        showDelimeter();

        questions.forEach(question -> {
            String questionWordingMessage = messageSource.getMessage("questionWording", new Object[]{question.getId(),
                    question.getQuestion()}, propertiesHolder.getCurrentLocale());
            System.out.println(questionWordingMessage);

            String answersMessage = messageSource.getMessage("answers", new Object[]{question.getAnswer(), userAnswers.get(question)},
                    propertiesHolder.getCurrentLocale());
            System.out.println(answersMessage);

            String questionResultMessage = messageSource.getMessage(result.get(question) ? "rightAnswer" : "wrongAnswer", null,
                    propertiesHolder.getCurrentLocale());
            System.out.println(questionResultMessage);

            showDelimeter();
        });

        long rightAnswers = result.values().stream().filter(Boolean::booleanValue).count();
        long questionsNumber = questions.size();
        String resultMessage = messageSource.getMessage("result", new Object[]{rightAnswers, questionsNumber},
                propertiesHolder.getCurrentLocale());
        System.out.println(resultMessage);

        if (rightAnswers >= propertiesHolder.getValueToPassTheTest()) showPassTheTestMessage();
        else showFailTheTestMessage();

        String buyMessage = messageSource
                .getMessage("buy", new Object[]{userInfo.getName(), userInfo.getSurName()}, propertiesHolder.getCurrentLocale());
        System.out.println(buyMessage);
    }

    private void showPassTheTestMessage() {
        String passTheTestMessage = messageSource.getMessage("passTheTest", null, propertiesHolder.getCurrentLocale());
        System.out.println(passTheTestMessage);
    }

    private void showFailTheTestMessage() {
        String failTheTestMessage = messageSource.getMessage("failTheTest", null, propertiesHolder.getCurrentLocale());
        System.out.println(failTheTestMessage);
    }

    private void showDelimeter() {
        String delimeterMessage = messageSource.getMessage("delimeter", null, propertiesHolder.getCurrentLocale());
        System.out.println(delimeterMessage);
    }
}
