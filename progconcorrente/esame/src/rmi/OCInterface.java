package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
    Prendere tutti i metodi dell'oggetto convidiso ed aggiungerli all'interfaccia con
    throws RemoteException
    Mettere extends Remote
 */
public interface OCInterface extends Remote {

    public void metodo1() throws RemoteException;
    public int metodo2() throws RemoteException;

}
