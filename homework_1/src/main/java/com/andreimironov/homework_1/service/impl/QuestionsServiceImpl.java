package com.andreimironov.homework_1.service.impl;

import com.andreimironov.homework_1.Utils;
import com.andreimironov.homework_1.domain.Question;
import com.andreimironov.homework_1.service.QuestionsService;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class QuestionsServiceImpl implements QuestionsService {
    private final String questionsPath;

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
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(this.questionsPath);
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
