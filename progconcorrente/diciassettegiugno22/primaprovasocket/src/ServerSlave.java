import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSlave extends Thread {

    Socket socket;
    Coda coda;
    ObjectInputStream in;
    ObjectOutputStream out;

    public ServerSlave(Socket s, Coda c){
        this.socket = s;
        this.coda = c;
    }

    private void exec(String comando) throws IOException, ClassNotFoundException, InterruptedException {
        Elemento elemento;
        if(comando.equals("produzione")){
            elemento = (Elemento) in.readObject();
            coda.setItem(elemento);
        }

        if(comando.equals("consumazione")){
            elemento = coda.getItem();
            out.writeObject(elemento);
        }

        if(comando.equals("lettura")){

        }

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
                    str = (String) in.readObject();
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                try {
                    exec(str);
                } catch (IOException | ClassNotFoundException | InterruptedException e) {
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
