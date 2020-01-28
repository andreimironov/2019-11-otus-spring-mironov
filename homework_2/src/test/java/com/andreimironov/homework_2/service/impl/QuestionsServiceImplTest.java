package com.andreimironov.homework_2.service.impl;

import com.andreimironov.homework_2.bean.PropertiesHolder;
import com.andreimironov.homework_2.domain.Question;
import com.andreimironov.homework_2.service.QuestionsService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Locale;

public class QuestionsServiceImplTest {
    @Test
    public void testGetQuestions() {
        PropertiesHolder propertiesHolder = Mockito.mock(PropertiesHolder.class);
        Mockito.when(propertiesHolder.getCurrentLocale()).thenReturn(Locale.ENGLISH);
        Mockito.when(propertiesHolder.getQuestionsPath()).thenReturn("questions_en_EN.csv");
        QuestionsService questionsService = new QuestionsServiceImpl(propertiesHolder);

        List<Question> questions = questionsService.getQuestions();
        Assertions.assertThat(questions).containsExactly(
                Question.builder().id(null).question("q2").answer("a2").build(),
                Question.builder().id(null).question("q4").answer("a4").build(),
                Question.builder().id(1).question("q1").answer("a1").build(),
                Question.builder().id(3).question("q3").answer("a3").build()
        );
    }
}
