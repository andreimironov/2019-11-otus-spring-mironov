package com.andreimironov.homework_2.component;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Getter
@Component
public class LocaleHolder {
    private final Locale currentLocale;
    
    public LocaleHolder(@Value("${locale.current}") Locale currentLocale) {
        this.currentLocale = currentLocale;
    }
}
