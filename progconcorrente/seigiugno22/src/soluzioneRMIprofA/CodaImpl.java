package soluzioneRMIprofA;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CodaImpl extends UnicastRemoteObject implements CodaInterf {
	private static final long serialVersionUID = 1L;
	Coda laCoda;
	protected CodaImpl() throws RemoteException {
		super();
		laCoda=new Coda(4);
	}
	public Elemento getItem() throws RemoteException {
		return laCoda.getItem();
	}
	public void setItem(Elemento v) throws RemoteException {
		laCoda.setItem(v);
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
}
