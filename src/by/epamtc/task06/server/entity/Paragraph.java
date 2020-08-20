package by.epamtc.task06.server.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Paragraph implements Component, Serializable {
    private List<Sentence> sentences = new ArrayList<Sentence>();

    public Paragraph() {}

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public void addSentences(Sentence sentencesArray[]){
        for (int i = 0; i < sentencesArray.length; i++) {
            sentences.add(sentencesArray[i]);
        }
    }

    public List<Sentence> getSentences() {
        return sentences;
    }

    public void setSentences(List<Sentence> sentences) {
        this.sentences = sentences;
    }

    @Override
    public void print() {
        for (Sentence sentence: sentences){
            sentence.print();
        }
        System.out.println();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paragraph)) return false;
        Paragraph paragraph = (Paragraph) o;
        return Objects.equals(sentences, paragraph.sentences);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sentences);
    }

    @Override
    public String toString() {
        return "Paragraph{" +
                "sentences=" + sentences +
                '}';
    }
}
