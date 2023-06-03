package soluzioneRMIprofA;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Lettore {
	Elemento v;
	int numIterations;
	Random rnd;
	String name;
	public Lettore(int n){
		numIterations=n;
		rnd=new Random();
		name="lett"+rnd.nextInt(1000);
	}
	private void exec() throws RemoteException, NotBoundException{
		Registry reg=LocateRegistry.getRegistry(1099);
		CodaInterf buffer= (CodaInterf) reg.lookup("SERVER");
		for(int i=0; i<numIterations; i++){
			v=buffer.readItem(1);
			try {
				Thread.sleep(rnd.nextInt(2000));
			} catch (InterruptedException e) { }
		}
		System.out.println(name+": termino");
	}
	public static void main(String[] args) {
		try {
			new Lettore(8).exec();
		} catch (RemoteException | NotBoundException e) {
			System.out.println("Consumatore KO");
		}
	}
}

