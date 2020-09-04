package by.epamtc.task06.server.dao;

import by.epamtc.task06.server.entity.Text;

import java.io.IOException;

public interface TextDAO {
    Text getText() throws IOException;
}
