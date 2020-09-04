package by.epamtc.task06.server.entity;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class Sentence implements Component, Serializable {
    private List<Word> words = new ArrayList<Word>();
    private static String punctuationMarksRegex;
    private static String openParenthesisRegex;
    private static String closingParenthesisRegex;
    private static String PATH_TO_REGEX = "resources\\config.properties";

    static{
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH_TO_REGEX);
            Properties properties = new Properties();
            properties.load(fileInputStream);

            punctuationMarksRegex = properties.getProperty("PUNCTUATION_MARKS_REGEX");
            openParenthesisRegex = properties.getProperty("OPEN_PARENTHESIS_REGEX");
            closingParenthesisRegex = properties.getProperty("CLOSING_PARENTHESIS_REGEX");
        } catch (IOException e) {
            e.printStackTrace();
            punctuationMarksRegex = "^(?i:[,:;'.!?]).*";
            openParenthesisRegex = "^(?i:[(]).*";
            closingParenthesisRegex = "^(?i:[)]).*";
        }
    }

    public Sentence(){}

    public void addWord(Word word){
        words.add(word);
    }

    public void delWord(Word word) {
        words.remove(word);
    }

    public void addWords(Word wordsArray[]){
        for (int i = 0; i < wordsArray.length; i++) {
            words.add(wordsArray[i]);
        }
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    @Override
    public void print() {
        for (int i = 0; i < (words.size() - 1); i++){

            if (words.get(i).getWord().matches(openParenthesisRegex)){
                words.get(i).print();
                continue;
            }

            if (words.get(i+1).getWord().matches(closingParenthesisRegex)){
                words.get(i).print();
                i++;
            }

            if (words.get(i+1).getWord().matches(punctuationMarksRegex)){
                words.get(i).print();
                words.get(i+1).print();
                System.out.print(" ");
                i++;
            }
            else {
                words.get(i).print();
                System.out.print(" ");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sentence)) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(words, sentence.words);
    }

    @Override
    public int hashCode() {
        return Objects.hash(words);
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "words=" + words +
                '}';
    }
}
