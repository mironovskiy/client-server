package by.epamtc.task06.client;

import by.epamtc.task06.server.entity.TextDTO;
import by.epamtc.task06.server.entity.Word;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    static ObjectOutputStream objectOutputStream;
    static ObjectInputStream objectInputStream;
    private static final int PORT = 8000;
    private static final String HOST = "127.0.0.1";
    static Socket socket;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        socket = new Socket(HOST, PORT);

        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());

        TextDTO textDTO = new TextDTO();

        //запрос для получения исходного текста
        textDTO.setCodeOperation(1);
        objectOutputStream.writeObject(textDTO);
        textDTO = (TextDTO) objectInputStream.readObject();
        textDTO.getText().print();

        //запрос для полученмя текста, в котором в предложениях первое слово заменено с последним
        textDTO.setCodeOperation(2);
        objectOutputStream.writeObject(textDTO);
        textDTO = (TextDTO) objectInputStream.readObject();
        textDTO.getText().print();

        //запрос для получения текста, в котором удалены все слова заданной длины, начинающиеся на согласную букву
        textDTO.setCodeOperation(3);
        textDTO.setWordLength(5);
        objectOutputStream.writeObject(textDTO);
        textDTO = (TextDTO) objectInputStream.readObject();
        textDTO.getText().print();

        //запрос для получения текста, в котором слова заданной длины заменены указанной подстрокой
        textDTO.setCodeOperation(4);
        textDTO.setWordLength(5);
        textDTO.setWord(new Word("WORD"));
        objectOutputStream.writeObject(textDTO);
        textDTO = (TextDTO) objectInputStream.readObject();
        textDTO.getText().print();

        textDTO.setCodeOperation(-1);
        objectOutputStream.writeObject(textDTO);
    }
}
