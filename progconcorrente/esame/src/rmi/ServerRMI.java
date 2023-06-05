package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*
    1.fare extends UnicastRemoteObject implements ServerInterface
    2.passare come parametro l'oggetto condiviso
    3.nel costruttore inserire: super() e istanziare l'oggetto condiviso
 */
public class ServerRMI extends UnicastRemoteObject implements OCInterface {

    OggettoCondiviso oc;

    public ServerRMI() throws RemoteException {
        super();
        oc = new OggettoCondiviso();
    }

    @Override
    public void metodo1() throws RemoteException {
        oc.metodo1();
    }

    @Override
    public int metodo2() throws RemoteException {
        return oc.metodo2();
    }
}
