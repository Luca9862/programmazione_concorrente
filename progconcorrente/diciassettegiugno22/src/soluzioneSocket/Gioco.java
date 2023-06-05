package soluzioneSocket;

import java.util.Random;

public class Gioco {
	private int estratto;
	private Random rnd;
	public final int MAX_NUM=10;
	private boolean scommesseAperte;
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
	public synchronized boolean piazzaScommessa(int id, DatoScommessa sc) {
		if(scommesseAperte) {
			// qui bisognerebbe memorizzare la scommessa, ma per semplicita` non lo facciamo
			System.out.println("Gioco: ricevuto da giocatore "+id+" "+sc);
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
