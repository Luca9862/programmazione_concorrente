package soluzioneRMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CodaServer extends UnicastRemoteObject implements CodaInterface {
    public static final long serialVersionUID = 1L;
    Coda coda;

    CodaServer() throws RemoteException {
        super();
        coda = new Coda(4);
    }
    @Override
    public Elemento getItem() throws RemoteException, InterruptedException {
        return coda.getItem();
    }

    @Override
    public void setItem(Elemento e) throws RemoteException, InterruptedException {
        coda.setItem(e);
    }

    @Override
    public void readItem() throws RemoteException, InterruptedException {
        coda.readItem();
    }

    public static void main(String[] args) throws RemoteException {
        Registry reg = LocateRegistry.createRegistry(1099);
        CodaServer server = new CodaServer();
        reg.rebind("CodaServer", server);
    }
}
