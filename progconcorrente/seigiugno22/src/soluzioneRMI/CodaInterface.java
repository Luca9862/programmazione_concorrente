package soluzioneRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CodaInterface extends Remote {
    Elemento getItem() throws RemoteException, InterruptedException;
    void setItem(Elemento e) throws RemoteException, InterruptedException;
    void readItem() throws RemoteException, InterruptedException;
}
