package by.epamtc.task06.server.dao.parser;

import by.epamtc.task06.server.entity.Component;

import java.io.IOException;

public interface ParserDAO {
    Component[] parse(String text) throws IOException;
}
