package soluzioneThread;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class MainGioco {
	static final int numGiocatori=4;
	Random rnd;

	public static void main(String[] args) {

		//new MainGioco().exec();

		Partita partita = new Partita(numGiocatori);
		CyclicBarrier c1 = new CyclicBarrier(numGiocatori);
		CyclicBarrier c2 = new CyclicBarrier(numGiocatori);

		for(int i = 0; i<4; i++){
			new Giocatore(i, partita, c1, c2).start();
		}
	}
}
