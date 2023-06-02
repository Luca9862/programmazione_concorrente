package soluzionesocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
    Socket socket;
    Coda coda;
    ObjectInputStream in;
    ObjectOutputStream out;

    public ServerThread(Socket s, Coda c) throws IOException {
         socket = s;
         coda = c;
         in = new ObjectInputStream(socket.getInputStream());
         out = new ObjectOutputStream(socket.getOutputStream());
         start();
    }

    private void exec(String str) throws InterruptedException, IOException, ClassNotFoundException {
        Elemento elemento;
        if(str.equals("Produzione")){
            elemento = (Elemento) in.readObject();
            coda.setItem(elemento);
        }
        if(str.equals("Consumazione")){
            elemento = coda.getItem();
            out.writeObject(elemento);
        }
        if(str.equals("Lettura")){
            elemento = coda.readItem();
            out.writeObject(elemento);
        }
    }

    public void run(){
        boolean finito = false;
        String str;
        try{
            while(!finito){
                str = (String) in.readObject();
                if(str.equals("END")){
                    finito = true;
                }else{
                    System.out.println("Salve esegue: " + str);
                    exec(str);
                }
            }
        }catch (IOException | ClassNotFoundException e){
            System.out.println("I/O_ERROR");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
