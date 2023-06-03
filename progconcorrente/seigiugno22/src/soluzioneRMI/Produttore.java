package soluzioneRMI;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Produttore {
	int numIterations;
	String name;
	Random rnd;
	public Produttore(int n){
		numIterations=n;
		rnd = new Random();
		name = "prod" + rnd.nextInt(1000);
	}
	public void exec() throws RemoteException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry(1099);
		CodaInterface buffer = (CodaInterface) reg.lookup("CodaServer");
		for(int i=0; i<numIterations; i++){
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(300));
			} catch (InterruptedException e) { }
			try {
				buffer.setItem(new Elemento("el_"+i, ThreadLocalRandom.current().nextInt(300)));
			} catch (InterruptedException | RemoteException e) {
				throw new RuntimeException(e);
			}
		}
		System.out.println(name+": termino");
	}

	public static void main(String[] args) {
		try {
			new Produttore(8).exec();
		} catch (RemoteException | NotBoundException e) {
			System.out.println("Consumatore KO");
		}
	}
}


