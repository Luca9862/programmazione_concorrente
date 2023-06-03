package soluzioneRMIprofB;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Produttore {
	Elemento v;
	int numIterations;
	Random rnd;
	String name;
	public Produttore(int n){
		numIterations=n;
		rnd=new Random();
		name="prod"+rnd.nextInt(1000);
	}
	private void exec() throws RemoteException, NotBoundException{
		Registry reg=LocateRegistry.getRegistry(1099);
		CodaInterf buffer= (CodaInterf) reg.lookup("SERVER");
		for(int i=0; i<numIterations; i++){
			v=new Elemento("p1_"+i, rnd.nextInt(100));
			System.out.println(name+" ho prodotto "+v);
			buffer.setItem(v);
			try {
				Thread.sleep(200+rnd.nextInt(500));
			} catch (InterruptedException e) { }
		}
		System.out.println(name+": termino");
	}
	public static void main(String[] args) {
		try {
			new Produttore(8).exec();
		} catch (RemoteException | NotBoundException e) {
			System.out.println("Produttore KO");
			e.printStackTrace();
		}
	}


}


