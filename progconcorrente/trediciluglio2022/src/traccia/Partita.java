package traccia;

import java.util.Random;

public class Partita {
	int numMani;
	Random rnd;
	int numGiocatori;
	int numGiocateManoCorrente;
	int ordineGiocatoriMano[]={0,1,2,3};
	SituazionePartita statoPartita;
	public Partita(int n) {
		rnd=new Random();
		numMani=2+rnd.nextInt(4);
		numGiocatori=n;
		statoPartita=new SituazionePartita();
		reset();
		System.out.println("**** inizia partita di "+numMani+" mani");
	}
	private void riordinaGiocatori() {
		// rimescoliamo ordine giocatori
		// inessenziale: da non considerare
		int da, a, ti;
		for(int i=0; i<numGiocatori-1; i++) {
			da=rnd.nextInt(numGiocatori);
			a=rnd.nextInt(numGiocatori);
			ti=ordineGiocatoriMano[a];
			ordineGiocatoriMano[a]=ordineGiocatoriMano[da];
			ordineGiocatoriMano[da]=ti;
		}
	}
	private void reset() {
		numGiocateManoCorrente=0;
		riordinaGiocatori();
	}
	public int numMani() {
		return numMani;
	}
	public SituazionePartita leggiSituazione() {
		return statoPartita;
	}
	public synchronized boolean giocata(int idGiocatore, int x) {
		
		// da completare/rivedere
		
		// l'effetto della giocata usando x e` inessenziale
		statoPartita.aggiornaSituazione("boh");
		numGiocateManoCorrente++;
		if(numGiocateManoCorrente>=numGiocatori) {
			reset();
		}
		return true;
	}
	public synchronized void aspettaTurno(int idGiocatore) {
		// da implementare
	}
}
