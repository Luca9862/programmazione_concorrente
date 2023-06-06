package rmi;

/*
    Questa classe rappresenta il Server RMI
        1.fare extends UnicastRemoteObject implements OCInterface
        2.passare come parametro l'oggetto condiviso
        3.nel costruttore inserire: super() e istanziare l'oggetto condiviso
        4.fare il main come indicato sotto

        main:
        public static void main(String[] args) throws RemoteException{
            Registry reg = LocateRegistry.createRegistry(1099);
            ServerImpl server = new ServerImpl();
            reg.rebind("server", server);
        }
 */

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements OCInterface {
    private static final long serialVersionUID = 1L;
    OggettoCondiviso oc;

    public ServerImpl() throws RemoteException {
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

    public static void main(String[] args) throws RemoteException{
        Registry reg = LocateRegistry.createRegistry(1099);
        ServerImpl server = new ServerImpl();
        reg.rebind("server", server);
    }
}
