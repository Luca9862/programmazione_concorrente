package soluzionesocket;

import soluzionesocketProf.CodaServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CodaServer {

    public static final int PORT = 2601;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        Coda coda = new Coda(4);
        while(true){
            Socket socket = ss.accept();
            System.out.println("SERVER CONNESSO");
            new CodaThread(socket, coda);
        }
    }
}
