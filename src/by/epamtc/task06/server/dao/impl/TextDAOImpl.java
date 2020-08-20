package by.epamtc.task06.server.dao.impl;

import by.epamtc.task06.server.dao.TextDAO;
import by.epamtc.task06.server.dao.parser.ParserDAO;
import by.epamtc.task06.server.dao.parser.impl.SentenceParser;
import by.epamtc.task06.server.entity.CodeBlock;
import by.epamtc.task06.server.entity.Paragraph;
import by.epamtc.task06.server.entity.Sentence;
import by.epamtc.task06.server.entity.Text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextDAOImpl implements TextDAO {
    private static final String FILE_PATH = "resources\\text.txt";
    private static final String CODE_BEGIN_REGEX = "([{])";
    private static final String CODE_END_REGEX = "([}])";

    @Override
    public Text getText() throws IOException {
        Text text = new Text();
        ParserDAO parserDAO = SentenceParser.getInstance();
        CodeBlock codeBlock;
        Paragraph paragraph;
        String textBlock;
        Pattern pattern = Pattern.compile(CODE_BEGIN_REGEX);
        Pattern pattern1 = Pattern.compile(CODE_END_REGEX);
        int braceCounter = 0;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH));

        while ((textBlock = bufferedReader.readLine()) != null){
            Matcher matcher = pattern.matcher(textBlock);

            if (matcher.find()){
                braceCounter++;
                codeBlock = new CodeBlock(textBlock);

                while (braceCounter != 0){
                    textBlock = bufferedReader.readLine();
                    codeBlock.addCode(textBlock);

                    matcher = pattern.matcher(textBlock);
                    if (matcher.find()){
                        braceCounter++;
                    }

                    matcher = pattern1.matcher(textBlock);
                    if (matcher.find()){
                        braceCounter--;
                    }
                }

                text.addComponent(codeBlock);
            }
            else {
                paragraph = new Paragraph();
                paragraph.addSentences((Sentence[]) parserDAO.parse(textBlock));
                text.addComponent(paragraph);
            }
        }

        return text;
    }
}
