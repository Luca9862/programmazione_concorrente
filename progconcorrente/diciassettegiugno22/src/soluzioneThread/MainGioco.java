package soluzioneThread;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class MainGioco {
	static final int numGiocatori=4;
	Random rnd;
	void exec() {
		rnd=new Random();
		Partita laPartita=new Partita(numGiocatori);
		for(int j=0; j<laPartita.numMani(); j++) {
			for(int id=0; id<numGiocatori; id++) {
				System.out.println("Giocatore "+id+" gioco la mano "+j);
				laPartita.giocata(id, rnd.nextInt(10));
			}
			for(int id=0; id<numGiocatori; id++) {
				System.out.println("Giocatore "+id+" ho fatto "+laPartita.puntiMano(id)+
						" punti in questa mano");
			}
		}
		System.out.println("partita terminata");
	}
	public static void main(String[] args) {

		//new MainGioco().exec();

		Partita partita = new Partita(4);
		CyclicBarrier c1 = new CyclicBarrier(4);
		CyclicBarrier c2 = new CyclicBarrier(4);

		for(int i = 0; i<4; i++){
			new Giocatore(i, partita, c1, c2).start();
		}
	}
}
