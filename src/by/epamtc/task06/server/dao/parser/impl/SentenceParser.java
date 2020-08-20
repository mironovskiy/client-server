package by.epamtc.task06.server.dao.parser.impl;

import by.epamtc.task06.server.dao.parser.ParserDAO;
import by.epamtc.task06.server.entity.Sentence;
import by.epamtc.task06.server.entity.Word;

public class SentenceParser implements ParserDAO {
    private static final SentenceParser instance = new SentenceParser();
    private static final String SENTENCE_REGEX = "(?<=[.?!])\\s";


    private SentenceParser(){};

    public static SentenceParser getInstance() {
        return instance;
    }

    @Override
    public Sentence[] parse(String paragraph) {
        Sentence sentencesArr[];
        WordParser wordParser = WordParser.getInstance();

        String sentences[] = paragraph.split(SENTENCE_REGEX);
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
