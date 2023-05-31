package soluzionesocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8080;
    public static final int num_giocatori = 4;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        Partita partita = new Partita(num_giocatori);
        try {
            while (true) {
                Socket s = ss.accept();
                System.out.println("Server pronto");
                new ServerThread(s, partita).start();
            }
        } finally {
            ss.close();
        }
    }
}
