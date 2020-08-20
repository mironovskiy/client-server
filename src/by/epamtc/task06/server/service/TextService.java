package by.epamtc.task06.server.service;

import by.epamtc.task06.server.entity.Text;
import by.epamtc.task06.server.entity.Word;

import java.io.IOException;

public interface TextService {
    Text getText() throws IOException;
    Text swapFirstAndLastWordInSentence() throws IOException;
    Text delWordsStartingWithConsonant(int wordLength) throws IOException;
    Text replaceWords(int wordLength, Word newWord) throws IOException;
}
