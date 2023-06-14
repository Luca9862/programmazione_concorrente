package es_3;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements DatiInterface {
    private static final long serialVersionUID = 1L;
    Dati dati;

    public ServerImpl() throws RemoteException {
        super();
        dati = new Dati();
    }

    @Override
    public void aggiungiDato(String key, String info) throws RemoteException {
        dati.aggiungiDato(key, info);
    }

    @Override
    public void eliminaDato(String key) throws RemoteException {
        dati.eliminaDato(key);
    }
    
    public boolean esisteDato(String key) throws RemoteException {
    	return dati.esisteDato(key);
    }
    
    public String trovaDato(String key) throws RemoteException {
    	return dati.trovaDato(key);
    }

    public static void main(String[] args) throws RemoteException{
        Registry reg = LocateRegistry.createRegistry(1099);
        ServerImpl server = new ServerImpl();
        reg.rebind("server", server);
    }
}
