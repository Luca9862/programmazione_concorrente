package soluzionesocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        System.out.println("SERVER PRONTO");
        Coda coda = new Coda(4);
        try{
            while(true){
                Socket s = ss.accept();
                System.out.println("CONNESSO AL CLIENT");
                new ServerThread(s, coda);
            }
        } finally {
            ss.close();
        }


    }
}
