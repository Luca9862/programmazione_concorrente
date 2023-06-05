package soluzioneSocketprof;

import java.util.Random;

public class Gioco {
	private int estratto;
	private Random rnd;
	public static final int MAX_NUM=10;
	private boolean scommesseAperte=false;
	Gioco(){
		estratto=-1;
		rnd=new Random();
		scommesseAperte=true;
	}
	public synchronized void faiEstrazione() {
		scommesseAperte=false;
		estratto=rnd.nextInt(MAX_NUM);
		System.out.println("Gioco: estratto il "+estratto);
		notifyAll();
	}
	public synchronized int leggiEstratto() {
		while(scommesseAperte) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		return estratto;
	}
	public synchronized boolean piazzaScommessa(DatoScommessa sc) {
		if(scommesseAperte) {
			// qui bisognerebbe memorizzare la scommessa, ma per semplicita` non lo facciamo
			System.out.println("Gioco: ricevuta "+sc);
			return true;
		} else {
			return false;
		}		
	}
	public synchronized void aperturaScommesse() {
		estratto=-1;
		scommesseAperte=true;
	}
}
