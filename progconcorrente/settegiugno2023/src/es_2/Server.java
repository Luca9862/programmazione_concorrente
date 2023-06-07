package es_2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final static int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        System.out.println("Server in ascolto");
        Dati dati = new Dati();
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("connessione accettata");
                new ServerSlave(socket, dati);
            }
        }finally {
            serverSocket.close();
        }
    }
}
