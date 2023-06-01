package soluzionesocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSlave extends Thread {

    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    Deposito depositoIn;
    Deposito depositoOut;

    public ServerSlave(Socket s, Deposito in, Deposito out) throws IOException {
        this.socket = s;
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
        start();
    }

    private void exec(String comando) throws IOException, ClassNotFoundException, InterruptedException {
        RichiestaRisposta rr;
        if(comando.equals("LetturaRichiesta")){
            rr = this.depositoOut.LeggiQualunque();
            out.writeObject(rr);
        }
        if(comando.equals("LetturaRisposta")){
            int idx =(int) in.readObject();
            rr = depositoOut.LeggiIdx(idx);
            out.writeObject(rr);
        }
        if (comando.equals("AggiuntaRichiesta")) {
            rr = (RichiestaRisposta) in.readObject();
            depositoIn.Aggiungi(rr);
        }

        if(comando.equals("AggiuntaRisposta")){
            rr = (RichiestaRisposta) in.readObject();
            depositoOut.Aggiungi(rr);
        }
    }

    public void run() {
        boolean finito = false;
        String str;
        while(!finito){
            try {
                str = (String) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if(str.equals("END")){
                finito = true;
            }else{
                try {
                    exec(str);
                } catch (IOException | ClassNotFoundException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("closing...");
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
