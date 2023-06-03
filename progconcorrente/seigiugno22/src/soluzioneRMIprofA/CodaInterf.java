package soluzioneRMIprofA;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CodaInterf extends Remote {
	public Elemento getItem() throws RemoteException;
	public void setItem(Elemento v) throws RemoteException;
	public Elemento readItem(int t) throws RemoteException;
}
