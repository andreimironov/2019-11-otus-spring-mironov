package com.andreimironov.homework_2.bean;

import lombok.Data;

import java.util.Locale;

@Data
public class PropertiesHolder {
    private final Locale currentLocale;
    private final String questionsPath;
    private final Long valueToPassTheTest;
}
