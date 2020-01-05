package com.andreimironov.homework_1.service.impl;

import com.andreimironov.homework_1.Utils;
import com.andreimironov.homework_1.domain.Question;
import com.andreimironov.homework_1.service.QuestionsService;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class QuestionsServiceImpl implements QuestionsService {
    private final String questionsPath;

    @Override
    public List<Question> getQuestions() {
        try {
            BufferedReader reader = getBufferedReader();
            CSVReader csvReader = getCsvReader(reader);
            return csvReader.readAll()
                            .stream()
                            .map(Utils::getQuestionFromCsvLine)
                            .sorted(Comparator.comparing(Question::getId, Comparator.nullsFirst(Integer::compareTo))
                                              .thenComparing(Question::getQuestion))
                            .collect(Collectors.toList());
        } catch (URISyntaxException | IOException e) {
            return Collections.emptyList();
        }
    }

    private BufferedReader getBufferedReader() throws IOException, URISyntaxException {
        Path questionsPath = Paths.get(ClassLoader.getSystemResource(this.questionsPath).toURI());
        return Files.newBufferedReader(questionsPath);
    }

    private CSVReader getCsvReader(BufferedReader reader) {
        CSVParser csvParser = new CSVParserBuilder().withSeparator(',')
                                                    .withIgnoreQuotations(true)
                                                    .build();
        return new CSVReaderBuilder(reader).withSkipLines(1)
                                           .withCSVParser(csvParser)
                                           .build();
    }
}
