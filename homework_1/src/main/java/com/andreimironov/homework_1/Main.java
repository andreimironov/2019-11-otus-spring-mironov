package com.andreimironov.homework_1;

import com.andreimironov.homework_1.domain.Question;
import com.andreimironov.homework_1.domain.UserInfo;
import com.andreimironov.homework_1.service.InputService;
import com.andreimironov.homework_1.service.OutputService;
import com.andreimironov.homework_1.service.QuestionsService;
import com.andreimironov.homework_1.service.ValidationService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        InputService inputService = context.getBean(InputService.class);
        OutputService outputService = context.getBean(OutputService.class);
        QuestionsService questionsService = context.getBean(QuestionsService.class);
        ValidationService validationService = context.getBean(ValidationService.class);

        List<Question> questions = questionsService.getQuestions();

        outputService.showHelloMessage();
        UserInfo userInfo = inputService.getUserInfo();
        outputService.showStartMessage(userInfo);

        Map<Question, String> userAnswers = inputService.getUserAnswers(questions);
        Map<Question, Boolean> result = validationService.getResult(userAnswers);

        outputService.showSummation(userInfo, questions, userAnswers, result);
    }
}
