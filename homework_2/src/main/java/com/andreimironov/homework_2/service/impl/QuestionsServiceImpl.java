package com.andreimironov.homework_2.service.impl;

import com.andreimironov.homework_2.Utils;
import com.andreimironov.homework_2.component.LocaleHolder;
import com.andreimironov.homework_2.domain.Question;
import com.andreimironov.homework_2.service.QuestionsService;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionsServiceImpl implements QuestionsService {
    private final LocaleHolder localeHolder;
    private final MessageSource messageSource;

    @Override
    @SneakyThrows(IOException.class)
    public List<Question> getQuestions() {
        Reader reader = getReader();
        CSVReader csvReader = getCsvReader(reader);

        return csvReader.readAll()
                        .stream()
                        .map(Utils::getQuestionFromCsvLine)
                        .filter(Objects::nonNull)
                        .sorted(Comparator.comparing(Question::getId, Comparator.nullsFirst(Integer::compareTo))
                                          .thenComparing(Question::getQuestion))
                        .collect(Collectors.toList());
    }

    private Reader getReader() {
        String questionsPath = messageSource.getMessage("questions.path", null, localeHolder.getCurrentLocale());
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(questionsPath);
        return new InputStreamReader(Objects.requireNonNull(inputStream));
    }

    private CSVReader getCsvReader(Reader reader) {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(',')
                                                    .withIgnoreQuotations(true)
                                                    .build();
        return new CSVReaderBuilder(reader).withSkipLines(1)
                                           .withCSVParser(csvParser)
                                           .build();
    }
}
