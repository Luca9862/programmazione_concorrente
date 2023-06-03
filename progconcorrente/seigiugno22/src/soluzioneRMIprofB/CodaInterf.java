package soluzioneRMIprofB;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CodaInterf extends Remote {
	public Elemento getItem() throws RemoteException;
	public void setItem(Elemento v) throws RemoteException;
	public Elemento readItem(int t) throws RemoteException;
	public void nuovoLettore(LettoreInterf l) throws RemoteException;
	public void fineLettore(LettoreInterf l) throws RemoteException;
}
