package by.epamtc.task06.server.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8000;
    private ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.connection();
    }

    public Server() throws IOException {
        serverSocket = new ServerSocket(PORT);
    }

    public void connection() throws IOException {
        System.out.println("Ожидание клиента...");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            new Thread(new ClientHandler(socket)).start();
        }

    }
}
