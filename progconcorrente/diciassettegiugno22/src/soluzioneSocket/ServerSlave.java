package soluzioneSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSlave extends Thread {
    Socket socket;
    Gioco gioco;
    ObjectInputStream in;
    ObjectOutputStream out;
    public ServerSlave(Socket s, Gioco g) throws IOException {
        this.socket = s;
        this.gioco = g;
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        start();
    }

    private void exec(String comando) throws IOException, ClassNotFoundException {
        boolean esito;
        int id;
        DatoScommessa datoScommessa;
        int estratto;
        if(comando.equals("piazzaScommessa")){
            id = (int) in.readObject();
            datoScommessa = (DatoScommessa) in.readObject();
            esito = gioco.piazzaScommessa(id, datoScommessa);
            out.writeObject(esito);
        }
        if(comando.equals("leggiEstratto")){
            gioco.faiEstrazione();
            estratto = gioco.leggiEstratto(); //si blocca appena esegue questo
            out.writeObject(estratto);
            System.out.println("Estratto: " + estratto);
        }

        /*if(comando.equals("faiEstrazione")){
            gioco.faiEstrazione();
            out.writeObject("Estrazione effettuata");
        }*/
    }

    public void run() {
        boolean finito = false;
        String str;
        while (!finito) {
            try {
                str = (String) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (str.equals("END"))
                finito = true;
            else {
                try {
                    exec(str);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
