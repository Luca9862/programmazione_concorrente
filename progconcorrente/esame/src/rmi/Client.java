package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

/*
    Nell'exec del client aggiungere:

        Registry reg = LocateRegistry.getRegistry(1099);
        OCInterface server = (OCInterface) reg.lookup("server");

    rimuovere tutto ciò che riguarda i thread
    far eseguire a OCInterface server tutti i metodi dell'oggetto condiviso (come con i socket)

    e poi usare server come se fosse un oggetto locale sostituendolo nelle chiamate ai metodi dell'oggetto condiviso
 */
public class Client {
    Random rnd;
    OggettoCondiviso oc;
    String id;
    public Client(int n){
        rnd = new Random();
        id = String.valueOf(rnd.nextInt());
    }
    private void exec() throws RemoteException, NotBoundException {
        Registry reg = LocateRegistry.getRegistry(1099);
        OCInterface oc = (OCInterface) reg.lookup("SERVER");
        oc.metodo1();
        /*
            ...
        */
        oc.metodo2();
    }

    public static void main(String[] args) {
        try {
            new Client(8).exec();
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Consumatore KO");
        }
    }
}
