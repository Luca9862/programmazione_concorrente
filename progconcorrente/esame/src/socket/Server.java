package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final static int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        OggettoCondiviso oc = new OggettoCondiviso();
        try {
            while (true) {
                Socket cSocket = serverSocket.accept();
                System.out.println("server accepted connection");
                new ServerSlave(cSocket, oc);
            }
        }finally {
            serverSocket.close();
        }
    }
}
