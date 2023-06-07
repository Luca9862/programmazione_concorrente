package es_2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSlave extends Thread {
    Socket socket;
    Dati dati;
    ObjectInputStream in;
    ObjectOutputStream out;

    public ServerSlave(Socket s, Dati d) throws IOException {
        this.socket = s;
        this.dati = d;
        in = new ObjectInputStream(s.getInputStream());
        out = new ObjectOutputStream(s.getOutputStream());
        start();
    }

    public void exec(String comando) throws IOException, ClassNotFoundException {
        
        if (comando.equals("aggiungi")) {
           String nome = (String) in.readObject();
           String descrizioneString = (String) in.readObject();
           dati.aggiungiDato(nome, descrizioneString);
           System.out.println("Slave aggiunge: " + nome);
        }
        if (comando.equals("elimina")) {
            
        }
        
        if(comando.equals("trova")) {
        	String chiave = (String) in.readObject();
        	boolean esiste = dati.esisteDato(chiave);
        	out.writeObject(esiste);
        	out.writeObject(chiave);
            System.out.println("Slave ha ricercato la chiave: " + chiave);
        }
    }

    public void run() {
        String command = "";
        boolean finito = false;
        System.out.println("slave started");
        try {
            while(!finito) {
                command=(String) in.readObject();
                System.out.println("comando " + command + " ricevuto");
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
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) { }
        }
    }
}