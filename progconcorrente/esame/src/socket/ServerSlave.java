package socket;

/*
    1.Mettere extends Thread
    2.Dichiaro ObjectInputStream e ObjectOutputStream nel costruttore o nell'exec()
    3.Dichiaro il socket da passare come parametro
    4.Dichiaro la classe condivisa da passare come parametro
    5.Creo l'exec() dove gestisco le richieste da parte dei client
    6.Nel run() creo il while(true) dove leggo le richieste dei client
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSlave extends Thread {
    Socket socket;
    OggettoCondiviso oc;
    ObjectInputStream in;
    ObjectOutputStream out;

    public ServerSlave(Socket s, OggettoCondiviso r) throws IOException {
        this.socket = s;
        this.oc = r;
        in = new ObjectInputStream(s.getInputStream());
        out = new ObjectOutputStream(s.getOutputStream());
        start();
    }

    public void exec(String comando) throws IOException, ClassNotFoundException {
        Risorsa r;
        if (comando.equals("PRODUCI")) {
            try {
                r = (Risorsa) in.readObject();
                oc.setRisorsa(r);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (comando.equals("CONSUMA")) {
            try {
                r = oc.getRisorsa();
                out.writeObject(r);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        String command = "";
        boolean finito = false;
        System.out.println("slave started");
        try {
            while(!finito) {
                command=(String) in.readObject();
                System.out.println("command " + command + " received");
                if(command.equals("END")) {
                    finito = true;
                }
                else {
                    try {
                        exec(command);
                    } catch (ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (ClassNotFoundException | IOException e1) {
            e1.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) { }
        }
    }
}
