package soluzionesocket;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    Socket socket;
    Partita partita;
    ObjectInputStream in;
    ObjectOutputStream out;

    public ServerThread(Socket s, Partita p) throws IOException {
        this.socket = s;
        this.partita = p;
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
        start();
    }
    private void exec(String str) throws IOException, ClassNotFoundException, InterruptedException {
        int id = 0;
        if(str.equals("aspettaTurno")){
            id = (int)in.readObject();
            this.partita.aspettaTurno(id);
            out.writeObject(partita.leggiSituazione());
        }
        if(str.equals("giocata")){
            int mossa = (int) in.readObject();
            partita.giocata(id, mossa);
        }

        if(str.equals("numMani")){
            out.writeObject(partita.numMani());
        }
    }

    public void run(){
        boolean finito = false;
        String str = "";

        while(!finito){
            try {
                str = (String) in.readObject();
                if(str.equals("END"))
                    finito = true;
                else
                    exec(str);
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Closing");
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
