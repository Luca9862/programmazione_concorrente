package soluzioneRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Consumatore {
	Elemento v;
	int numIterations;
	Random rnd;
	String name ;
	public Consumatore(int n){
		numIterations=n;
		rnd = new Random();
		name = "cons"+rnd.nextInt(1000);
	}
	public void exec() throws RemoteException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry(1099);
		CodaInterface coda = (CodaInterface) reg.lookup("CodaServer");
		for(int i=0; i<numIterations; i++){
			try {
				v=coda.getItem();
			} catch (InterruptedException | RemoteException e) {
				throw new RuntimeException(e);
			}
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(300));
			} catch (InterruptedException e) { }
		}
		System.out.println(name+": termino");
	}

	public static void main(String[] args) {
		try {
			new Consumatore(8).exec();
		} catch (RemoteException | NotBoundException e) {
			System.out.println("Consumatore KO");
		}
	}
}

