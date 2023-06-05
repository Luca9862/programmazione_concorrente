package tracciaSocket;

import java.util.Random;

public class Giocatore extends Thread {
	private Random rnd;
	private int mioId;
	private Gioco ilGioco;
	Giocatore(int id, Gioco g){
		rnd=new Random();
		ilGioco=g;
		mioId=id;
	}
	public void run() {
		int estratto;
		DatoScommessa miaScommessa;
		int numScommesse=2+rnd.nextInt(8);  // numero di scommesse che il giocatore vuol fare
		for(int i=0; i<numScommesse; i++) {
			miaScommessa=new DatoScommessa(rnd.nextInt(ilGioco.MAX_NUM), 1+rnd.nextInt(10));
			boolean fatto=false;
			while(!fatto) {
				fatto=ilGioco.piazzaScommessa(mioId, miaScommessa);
				if(!fatto) {
					// la scommessa non e` stata accettata
					// aspettiamo un po' e poi riproviamo
					try { Thread.sleep(250); } catch (InterruptedException e) { }
				}
			}
			System.out.println("giocatore_"+mioId+": faccio "+miaScommessa);
			estratto=ilGioco.leggiEstratto();
			if(estratto==miaScommessa.getNumero()) {
				System.out.println("giocatore_"+mioId+": ho vinto!");
			} else {
				System.out.println("giocatore_"+mioId+": ho perso!");
			}
		}
		System.out.println("giocatore_"+mioId+": smetto");
	}
}
