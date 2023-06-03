package soluzioneRMIprofB;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class CodaImpl extends UnicastRemoteObject implements CodaInterf {
	private static final long serialVersionUID = 1L;
	Coda laCoda;
	List<LettoreInterf> iLettori;
	CodaImpl() throws RemoteException {
		super();
		laCoda=new Coda(4);
		iLettori=new ArrayList<LettoreInterf>();
	}
	public Elemento getItem() throws RemoteException {
		return laCoda.getItem();
	}
	private synchronized void notifica() throws RemoteException{
		for(LettoreInterf l: iLettori) {
			l.nuovoElemento(laCoda.readItem(-1));
		}
	}
	public void setItem(Elemento v) throws RemoteException {
		laCoda.setItem(v);
		notifica();
	}
	public Elemento readItem(int t) throws RemoteException {
		return laCoda.readItem(t);
	}
	public static void main(String[] args) {
		try {
			CodaImpl codaServer=new CodaImpl();
			Registry reg=LocateRegistry.createRegistry(1099);
			reg.rebind("SERVER", codaServer);
		} catch (RemoteException e) {
			System.out.println("server main failed");
		}
	}
	public synchronized void nuovoLettore(LettoreInterf l) throws RemoteException {
		iLettori.add(l);		
	}
	public synchronized void fineLettore(LettoreInterf l) throws RemoteException {
		iLettori.remove(l);
	}
}
