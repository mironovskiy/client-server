package by.epamtc.task06.server.dao.parser.impl;

import by.epamtc.task06.server.dao.parser.ParserDAO;
import by.epamtc.task06.server.entity.Sentence;
import by.epamtc.task06.server.entity.Word;

import java.io.*;
import java.util.Properties;


public class SentenceParser implements ParserDAO {
    private static final SentenceParser instance = new SentenceParser();
    private static String sentenceRegex;
    private static final String PATH_TO_REGEX = "resources\\config.properties";

    static{
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH_TO_REGEX);
            Properties properties = new Properties();
            properties.load(fileInputStream);

            sentenceRegex = properties.getProperty("SENTENCE_REGEX");
        } catch (IOException e) {
            e.printStackTrace();
            sentenceRegex = "(?<=[.?!])s";
        }
    }


    private SentenceParser(){};

    public static SentenceParser getInstance() {
        return instance;
    }

    @Override
    public Sentence[] parse(String paragraph) {
        Sentence sentencesArr[];
        WordParser wordParser = WordParser.getInstance();

        String sentences[] = paragraph.split(sentenceRegex);
        sentencesArr = new Sentence[sentences.length];

        for (int i = 0; i < sentences.length; i++) {
            Word words[] = wordParser.parse(sentences[i]);
            Sentence sentence = new Sentence();
            sentence.addWords(words);
            sentencesArr[i] = sentence;
        }

        return sentencesArr;
    }
}
