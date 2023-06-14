package soluzioneThreadProf;

import java.util.concurrent.ThreadLocalRandom;

public class ClienteDati extends Thread {

	int myId;
	Dati iDati;
	ClienteDati(int n, Dati d){
		myId=n;
		this.setName("client_"+myId);
		iDati=d;
	}
	private void mySleep(int i1, int i2) {
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(i1, i2));
		} catch (InterruptedException e) { }
	}
	public void run() {
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
	}

	public static void main(String[] args) {
		Dati iDati = new Dati();
		for(int j=0; j<4; j++) {
			new ClienteDati(j, iDati).start();
		}
	}
}
