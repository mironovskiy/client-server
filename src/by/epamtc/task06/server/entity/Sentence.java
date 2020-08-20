package by.epamtc.task06.server.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sentence implements Component, Serializable {
    private List<Word> words = new ArrayList<Word>();
    private static final String PUNCTUATION_MARKS_REGEX = "^(?i:[,:;'.!?]).*";
    private static final String OPEN_PARENTHESIS_REGEX = "^(?i:[(]).*";
    private static final String CLOSING_PARENTHESIS_REGEX = "^(?i:[)]).*";

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
            //words.get(i).print();

            if (words.get(i).getWord().matches(OPEN_PARENTHESIS_REGEX)){
                words.get(i).print();
                continue;
            }

            if (words.get(i+1).getWord().matches(CLOSING_PARENTHESIS_REGEX)){
                words.get(i).print();
                i++;
            }

            if (words.get(i+1).getWord().matches(PUNCTUATION_MARKS_REGEX)){
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
