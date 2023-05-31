package traccia;

import java.util.Random;

public class Giocatore extends Thread{
	Partita laPartita;
	int mioId;
	Random rnd;
	
	public Giocatore(int id, Partita p) {
		laPartita=p;
		mioId=id;
		rnd=new Random();
		start();
	}
	public void run() {
		int numMani=laPartita.numMani();
		int miaMossa;
		System.out.println("Giocatore "+mioId+": inizio");
		for(int j=0; j<numMani; j++) {
			System.out.println("Giocatore "+mioId+": aspetto");
			laPartita.aspettaTurno(mioId);
			SituazionePartita statoPartita=laPartita.leggiSituazione();
			// la mossa dovrebbe tenere conto della situazione, ma semplifichiamo...
			miaMossa=1+rnd.nextInt(9);
			System.out.println("Giocatore "+mioId+": gioco "+miaMossa);
			laPartita.giocata(mioId, miaMossa);
		}
	}

}
