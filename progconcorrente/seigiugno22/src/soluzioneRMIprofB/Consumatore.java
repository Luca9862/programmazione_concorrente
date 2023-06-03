package soluzioneRMIprofB;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Consumatore {
	Elemento v;
	int numIterations;
	Random rnd;
	String name;
	public Consumatore(int n){
		numIterations=n;
		rnd=new Random();
		name="cons"+rnd.nextInt(1000);
	}
	private void exec() throws RemoteException, NotBoundException{
		Registry reg=LocateRegistry.getRegistry(1099);
		CodaInterf buffer= (CodaInterf) reg.lookup("SERVER");
		for(int i=0; i<numIterations; i++){
			v=buffer.getItem();
			System.out.println(name+" ho consumato "+v);
			try {
				Thread.sleep(300+rnd.nextInt(2000));
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

