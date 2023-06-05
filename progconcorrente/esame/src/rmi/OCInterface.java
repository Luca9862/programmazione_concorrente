package rmi;

import java.rmi.RemoteException;

/*
    Prendere tutti i metodi dell'oggetto convidiso ed aggiungerli all'interfaccia con
    throws RemoteException
 */
public interface OCInterface {

    public void metodo1() throws RemoteException;
    public int metodo2() throws RemoteException;

}
