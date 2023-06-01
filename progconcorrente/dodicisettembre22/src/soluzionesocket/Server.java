package soluzionesocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        Deposito depositoIn = new Deposito();
        Deposito depositoOut = new Deposito();
        System.out.println("SERVER PRONTO");
        while(true){
            Socket s = ss.accept();
            System.out.println("CONNESSO");
            new ServerSlave(s, depositoIn, depositoOut);
        }
    }
}
