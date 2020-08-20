package by.epamtc.task06.server.service.impl;

import by.epamtc.task06.server.dao.DAOFactory;
import by.epamtc.task06.server.dao.TextDAO;
import by.epamtc.task06.server.entity.Paragraph;
import by.epamtc.task06.server.entity.Text;
import by.epamtc.task06.server.entity.Word;
import by.epamtc.task06.server.service.TextService;
import by.epamtc.task06.server.service.validation.Validator;

import java.io.IOException;

public class TextServiceImpl implements TextService {
    private static final String CONSONANT_REGEX = "^(?i:[bcdfghjklmnpqrstvwxz]).*";

    @Override
    public Text getText() throws IOException {

        DAOFactory factory = DAOFactory.getInstance();
        TextDAO textDAO = factory.getTextDAO();

        Text text;
        text = textDAO.getText();

        return text;
    }

    @Override
    public Text swapFirstAndLastWordInSentence() throws IOException {
        Text text;
        text = getText();

        for (int i = 0; i < text.getComponents().size(); i++) {
            if (text.getComponents().get(i).getClass() == (new Paragraph().getClass())){

                for (int j = 0; j < ((Paragraph)text.getComponents().get(i)).getSentences().size(); j++) {
                    Word firstWord;
                    Word lastWord;
                    int numberOfWordsInSentence;

                    numberOfWordsInSentence = ((Paragraph)text.getComponents().get(i)).getSentences().get(j).getWords().size() - 1;
                    firstWord = ((Paragraph)text.getComponents().get(i)).getSentences().get(j).getWords().get(0);
                    lastWord = ((Paragraph)text.getComponents().get(i)).getSentences().get(j).getWords().get(numberOfWordsInSentence - 1);

                    ((Paragraph) text.getComponents().get(i)).getSentences().get(j).getWords().set(0, lastWord);
                    ((Paragraph) text.getComponents().get(i)).getSentences().get(j).getWords().set((numberOfWordsInSentence - 1), firstWord);
                }

            }
        }

        return text;
    }

    @Override
    public Text delWordsStartingWithConsonant(int wordLength) throws IOException {
        if (!Validator.checkValueForNegativity(wordLength)){
            return null;
        }

        Text text;
        text = getText();

        for (int i = 0; i < text.getComponents().size(); i++) {
            if (text.getComponents().get(i).getClass() == (new Paragraph().getClass())){
                for (int j = 0; j < ((Paragraph)text.getComponents().get(i)).getSentences().size(); j++) {
                    for (int k = 0; k < ((Paragraph)text.getComponents().get(i)).getSentences().get(j).getWords().size(); k++) {

                        if (((Paragraph)text.getComponents().get(i)).getSentences().get(j).getWords().get(k).getWord().length() == wordLength){
                            if (((Paragraph)text.getComponents().get(i)).getSentences().get(j).getWords().get(k).getWord().matches(CONSONANT_REGEX)){
                                ((Paragraph)text.getComponents().get(i)).getSentences().get(j).delWord(((Paragraph)text.getComponents().get(i)).getSentences().get(j).getWords().get(k));
                            }
                        }

                    }
                }
            }
        }

        return text;
    }

    @Override
    public Text replaceWords(int wordLength, Word newWord) throws IOException {
        if (!Validator.checkValueForNegativity(wordLength)){
            return null;
        }

        Text text;
        text = getText();

        for (int i = 0; i < text.getComponents().size(); i++) {
            if (text.getComponents().get(i).getClass() == (new Paragraph().getClass())){
                for (int j = 0; j < ((Paragraph)text.getComponents().get(i)).getSentences().size(); j++) {
                    for (int k = 0; k < ((Paragraph)text.getComponents().get(i)).getSentences().get(j).getWords().size(); k++) {

                        if (((Paragraph)text.getComponents().get(i)).getSentences().get(j).getWords().get(k).getWord().length() == wordLength){
                            ((Paragraph)text.getComponents().get(i)).getSentences().get(j).getWords().set(k, newWord);
                        }

                    }
                }
            }
        }

        return text;
    }

}
