package by.epamtc.task06.server.dao.parser.impl;

import by.epamtc.task06.server.dao.parser.ParserDAO;
import by.epamtc.task06.server.entity.Word;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser implements ParserDAO {
    private static final WordParser instance = new WordParser();
    private static String wordRegex;
    private static final String PATH_TO_REGEX = "resources\\config.properties";

    static{
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH_TO_REGEX);
            Properties properties = new Properties();
            properties.load(fileInputStream);

            wordRegex = properties.getProperty("WORD_REGEX");
        } catch (IOException e) {
            e.printStackTrace();
            wordRegex = "(d+|[^ws]|w+)";
        }
    }

    private WordParser() {}

    public static WordParser getInstance() {
        return instance;
    }

    @Override
    public Word[] parse(String sentence) {
        List<Word> wordsList = new ArrayList<Word>();
        Word words[];

        Pattern patternWord = Pattern.compile(wordRegex);
        Matcher matcher = patternWord.matcher(sentence);

        while (matcher.find()) {
            Word word = new Word();
            word.setWord(matcher.group());
            wordsList.add(word);
        }

        words = new Word[wordsList.size()];
        for (int i = 0; i < words.length; i++) {
            words[i] = wordsList.get(i);
        }

        return words;
    }

}
