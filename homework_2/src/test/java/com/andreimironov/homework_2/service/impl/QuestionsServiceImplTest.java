package com.andreimironov.homework_2.service.impl;

import com.andreimironov.homework_2.component.LocaleHolder;
import com.andreimironov.homework_2.domain.Question;
import com.andreimironov.homework_2.service.QuestionsService;
import org.apache.logging.log4j.message.Message;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;

public class QuestionsServiceImplTest {
    @Test
    public void testGetQuestions() {
        LocaleHolder localeHolder = new LocaleHolder(Locale.ENGLISH);
        MessageSource messageSource = Mockito.mock(MessageSource.class);
        Mockito.when(messageSource.getMessage("questions.path", null, localeHolder.getCurrentLocale())).thenReturn("questions_en.csv");
        QuestionsService questionsService = new QuestionsServiceImpl(localeHolder, messageSource);

        List<Question> questions = questionsService.getQuestions();
        Assertions.assertThat(questions).containsExactly(
                Question.builder().id(null).question("q2").answer("a2").build(),
                Question.builder().id(null).question("q4").answer("a4").build(),
                Question.builder().id(1).question("q1").answer("a1").build(),
                Question.builder().id(3).question("q3").answer("a3").build()
        );
    }
}
