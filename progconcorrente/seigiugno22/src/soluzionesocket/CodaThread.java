package soluzionesocket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CodaThread extends Thread{

    Socket socket;
    Coda coda;
    ObjectOutputStream out;
    ObjectInputStream in;
    public CodaThread(Socket s, Coda c) {
        this.socket = s;
        this.coda = c;
    }
}
