package soluzioneSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static final int PORT = 8080;
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        Gioco gioco = new Gioco();
        try {
            System.out.println("Server in ascolto sulla porta " + PORT);
            while(true){
                Socket s = ss.accept();
                System.out.println("Connessione stabilita con " + s.getInetAddress());
                new ServerSlave(s, gioco);
            }
        } finally {
            ss.close();
        }
    }
}
