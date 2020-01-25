package com.andreimironov.homework_2;

import com.andreimironov.homework_2.domain.Question;
import com.andreimironov.homework_2.domain.UserInfo;
import com.andreimironov.homework_2.service.InputService;
import com.andreimironov.homework_2.service.OutputService;
import com.andreimironov.homework_2.service.QuestionsService;
import com.andreimironov.homework_2.service.ValidationService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Map;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

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
