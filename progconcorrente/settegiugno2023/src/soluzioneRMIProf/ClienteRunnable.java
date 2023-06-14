package soluzioneRMIProf;

import java.rmi.RemoteException;
import java.util.concurrent.ThreadLocalRandom;

public class ClienteRunnable implements Runnable {

	int myId;
	DatiInterface iDati;
	ClienteRunnable(int n, DatiInterface d){
		myId=n;
		iDati=d;
	}
	private void mySleep(int i1, int i2) {
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(i1, i2));
		} catch (InterruptedException e) { }
	}
	public void run() {
		try {
			if((myId%2)==1) {

				iDati.aggiungiDato("potipu", "info potipu");

				mySleep(0, 2000);
				iDati.aggiungiDato("patagarru", "info patagarru");
				mySleep(0, 2000);
				iDati.aggiungiDato("svicolone", "info svicolone");
			} else {
				String chiaveDaCercare="svicolone";
				mySleep(0, 2000);
				System.out.println("Client"+myId+": cerco "+chiaveDaCercare);
				String info = iDati.trovaDato(chiaveDaCercare);
				System.out.println("Client"+myId+": info di "+chiaveDaCercare+" e` "+ info);
			}
		} catch (RemoteException e) {
			System.err.println("client runnable KO");
		}
	}
}
