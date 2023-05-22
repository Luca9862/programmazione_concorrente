package soluzionethread;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Giocatore extends Thread {
	private int mioId;
	private Partita laPartita;
	private Random rnd;
	private CyclicBarrier bar1, bar2;
	Giocatore(int id, Partita p, CyclicBarrier b1, CyclicBarrier b2){
		mioId=id;
		laPartita=p;
		rnd=new Random();
		bar1=b1;
		bar2=b2;
	}
	public void run() {
		int punti=0;
		int numMani=laPartita.numMani();
		System.out.println("Giocatore "+mioId+" inizio");
		for(int mano=0; mano<numMani; mano++) {
			System.out.println("Giocatore "+mioId+" gioco la mano "+mano);
			laPartita.giocata(mioId, rnd.nextInt(10));
			try {
				bar1.await();
			} catch (InterruptedException | BrokenBarrierException e) {}
			int puntiMano=laPartita.puntiMano(mano);
			try {
				bar2.await();				
			} catch (InterruptedException | BrokenBarrierException e) {}
			System.out.println("Giocatore "+mioId+" ho fatto "+puntiMano+" punti in questa mano");
			punti+=puntiMano;
		}
		System.out.println("Giocatore "+mioId+" ho fatto "+punti+" punti in totale");
	}
}
