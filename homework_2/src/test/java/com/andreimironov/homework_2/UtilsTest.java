package com.andreimironov.homework_2;

import com.andreimironov.homework_2.domain.Question;
import org.junit.Test;

import java.util.AbstractMap;

import static org.assertj.core.api.Assertions.assertThat;

public class UtilsTest {
    @Test
    public void testGetQuestionFromCsvLine() {
        assertThat(Utils.getQuestionFromCsvLine(null)).isNull();
        assertThat(Utils.getQuestionFromCsvLine(new String[]{})).isNull();
        assertThat(Utils.getQuestionFromCsvLine(new String[]{""})).isNull();
        assertThat(Utils.getQuestionFromCsvLine(new String[]{"", ""})).isNull();

        Question question = Utils.getQuestionFromCsvLine(new String[]{"not an integer", "question", "answer"});
        assertThat(question.getId()).isNull();
        assertThat(question.getQuestion()).isEqualTo("question");
        assertThat(question.getAnswer()).isEqualTo("answer");

        question = Utils.getQuestionFromCsvLine(new String[]{"1", "question", "answer"});
        assertThat(question.getId()).isEqualTo(1);
        assertThat(question.getQuestion()).isEqualTo("question");
        assertThat(question.getAnswer()).isEqualTo("answer");
    }

    @Test
    public void testValidate() {
        assertThat(Utils.validate(new AbstractMap.SimpleEntry<>(Question.builder().answer("1").build(), "1"))).isTrue();
        assertThat(Utils.validate(new AbstractMap.SimpleEntry<>(Question.builder().answer("1").build(), "1 "))).isTrue();
        assertThat(Utils.validate(new AbstractMap.SimpleEntry<>(Question.builder().answer(" 1").build(), "1 "))).isTrue();
        assertThat(Utils.validate(new AbstractMap.SimpleEntry<>(Question.builder().answer("answer   ").build(), "AnsWer "))).isTrue();
    }
}
