package soluzioneRMIprofB;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LettoreInterf extends Remote {
	public void nuovoElemento(Elemento e)throws RemoteException;
}
