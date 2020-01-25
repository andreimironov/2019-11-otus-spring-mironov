package com.andreimironov.homework_2.service.impl;

import com.andreimironov.homework_2.domain.Question;
import com.andreimironov.homework_2.service.QuestionsService;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;

public class QuestionsServiceImplTest {
    private final QuestionsService questionsService = new QuestionsServiceImpl("questions-test.csv");

    @Test
    public void testGetQuestions() {
        List<Question> questions = questionsService.getQuestions();
        Assertions.assertThat(questions).containsExactly(
                Question.builder().id(null).question("q2").answer("a2").build(),
                Question.builder().id(null).question("q4").answer("a4").build(),
                Question.builder().id(1).question("q1").answer("a1").build(),
                Question.builder().id(3).question("q3").answer("a3").build()
        );
    }
}
