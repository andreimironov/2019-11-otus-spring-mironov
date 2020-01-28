package com.andreimironov.homework_2.config;

import com.andreimironov.homework_2.bean.PropertiesHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;

import java.util.Locale;

@Configuration
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
public class Config {
    private final Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Bean
    public PropertiesHolder propertiesHolder() {
        Locale currentLocale = environment.getProperty("locales.current", Locale.class);
        String questionsPath = environment.getProperty("locales." + currentLocale.toString() + ".questionsPath");
        long valueToPassTheTest = environment.getProperty("valueToPassTheTest", Long.class);
        return new PropertiesHolder(currentLocale, questionsPath, valueToPassTheTest);
    }
}
