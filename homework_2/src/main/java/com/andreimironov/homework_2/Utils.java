package com.andreimironov.homework_2;

import com.andreimironov.homework_2.domain.Question;

import java.util.Map;

public class Utils {
    public static Question getQuestionFromCsvLine(String[] line) {
        if (line == null || line.length < 3) return null;

        Integer id = getIdFromString(line[0]);
        String question = line[1];
        String answer = line[2];

        return Question.builder().id(id).question(question).answer(answer).build();
    }

    private static Integer getIdFromString(String string) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static Boolean validate(Map.Entry<Question, String> entry) {
        Question question = entry.getKey();
        String answer = question.getAnswer();
        String userAnswer = entry.getValue();

        return answer.trim().toLowerCase().equals(userAnswer.trim().toLowerCase());
    }
}
