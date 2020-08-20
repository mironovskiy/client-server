package by.epamtc.task06.server.main;

import by.epamtc.task06.server.entity.Text;
import by.epamtc.task06.server.entity.TextDTO;
import by.epamtc.task06.server.service.ServiceFactory;
import by.epamtc.task06.server.service.TextService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private Socket socket;
    private TextDTO textDTO;
    private Text text;

    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    @Override
    public void run() {

        try {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            ServiceFactory factory = ServiceFactory.getInstance();
            TextService textService = factory.getTextService();

            while(true) {
                textDTO = (TextDTO) objectInputStream.readObject();
                System.out.print("Получен код ");

                switch (textDTO.getCodeOperation()) {
                    case -1:
                        return;
                    case 1:                       //получить исходный текст
                        System.out.println("1");
                        text = textService.getText();
                        textDTO.setText(text);
                        objectOutputStream.writeObject(textDTO);
                        break;
                    case 2:                      //заменить в предложениях первое слово с последним и вернуть полученный текст
                        System.out.println("2");
                        text = textService.swapFirstAndLastWordInSentence();
                        textDTO.setText(text);
                        objectOutputStream.writeObject(textDTO);
                        break;
                    case 3:                      //из текста удалить все слова заданной длины, начинающиеся на согласную букву
                        System.out.println("3");
                        text = textService.delWordsStartingWithConsonant(textDTO.getWordLength());
                        textDTO.setText(text);
                        objectOutputStream.writeObject(textDTO);
                        break;
                    case 4:                      //в некотором предложении текста слова заданной длины заменить указанной подстрокой, длина которой может не совпадать с длиной слова
                        System.out.println("4");
                        text = textService.replaceWords(textDTO.getWordLength(), textDTO.getWord());
                        textDTO.setText(text);
                        objectOutputStream.writeObject(textDTO);
                        break;
                }
            }


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }
}
