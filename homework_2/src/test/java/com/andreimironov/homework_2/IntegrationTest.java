package com.andreimironov.homework_2;

import com.andreimironov.homework_2.bean.PropertiesHolder;
import com.andreimironov.homework_2.config.Config;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

import java.util.Locale;

@SpringBootTest
@ContextConfiguration(classes = Config.class)
@TestPropertySource("classpath:application.properties")
public class IntegrationTest {
    @Autowired
    PropertiesHolder propertiesHolder;

    @Autowired
    MessageSource messageSource;

    @Test
    public void testPropertiesHolder() {
        Assertions.assertThat(propertiesHolder.getCurrentLocale()).isEqualTo(Locale.US);
        Assertions.assertThat(propertiesHolder.getQuestionsPath()).isEqualTo("questions_en_US.csv");
        Assertions.assertThat(propertiesHolder.getValueToPassTheTest()).isEqualTo(4);
    }

    @Test
    public void testMessageSource() {
        Assertions.assertThat(messageSource.getMessage("message", null, propertiesHolder.getCurrentLocale())).isEqualTo("test");
    }
}
