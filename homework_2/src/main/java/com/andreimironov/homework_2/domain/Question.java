package com.andreimironov.homework_2.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {
    private Integer id;
    private final String question;
    private final String answer;
}

