package soluzioneRMIprofB;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class LettoreImpl extends UnicastRemoteObject implements LettoreInterf {
	private static final long serialVersionUID = 1L;
	Elemento v;
	int numIterations;
	Random rnd;
	String name;
	CodaInterf buffer;
	public LettoreImpl(int n) throws RemoteException{
		super();
		v=new Elemento("non inizializzato", 0);
		numIterations=n;
		rnd=new Random();
		name="lett"+rnd.nextInt(1000);
	}
	private void exec() throws RemoteException, NotBoundException{
		Registry reg=LocateRegistry.getRegistry(1099);
		buffer= (CodaInterf) reg.lookup("SERVER");
		buffer.nuovoLettore(this);
		for(int i=0; i<numIterations; i++){
			System.out.println(name+" elemento="+v);
			try {
				Thread.sleep(500+rnd.nextInt(1000));
			} catch (InterruptedException e) { }
		}
		buffer.fineLettore(this);
		System.out.println(name+": termino");
		try { Thread.sleep(500); } catch (InterruptedException e) { }
		UnicastRemoteObject.unexportObject(this, false);
	}
	public static void main(String[] args) {
		try {
			new LettoreImpl(8).exec();
		} catch (RemoteException | NotBoundException e) {
			System.out.println("Consumatore KO");
		}
	}
	public void nuovoElemento(Elemento e) throws RemoteException {
		System.out.println(name+" ricevo elemento "+e);
		v=e;
	}
}

