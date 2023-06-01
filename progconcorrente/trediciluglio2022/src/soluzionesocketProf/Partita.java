package soluzionesocketProf;

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
	private void reset() {
		numGiocateManoCorrente=0;
		// rimescoliamo ordine giocatori
		int da, a, ti;
		for(int i=0; i<numGiocatori-1; i++) {
			da=rnd.nextInt(numGiocatori);
			a=rnd.nextInt(numGiocatori);
			ti=ordineGiocatoriMano[a];
			ordineGiocatoriMano[a]=ordineGiocatoriMano[da];
			ordineGiocatoriMano[da]=ti;
		}
		System.out.print("Partita: nuova mano, con ordine ");
		for(int j=0; j<numGiocatori; j++) {
			System.out.print(ordineGiocatoriMano[j]+" ");
		}
		System.out.println("");
	}
	public int numMani() {
		return numMani;
	}
	public SituazionePartita leggiSituazione() {
		return statoPartita;
	}
	public synchronized boolean giocata(int idGiocatore, int x) {
		if(idGiocatore==ordineGiocatoriMano[numGiocateManoCorrente]) {
			// l'effetto della giocata usando x non e` implementato
			statoPartita.aggiornaSituazione("boh");
			numGiocateManoCorrente++;
			if(numGiocateManoCorrente>=numGiocatori) {
				reset();
			}
			notifyAll();
			return true;
		} else {
			return false;
		}
	}
	public synchronized void aspettaTurno(int idGiocatore) {
		while(idGiocatore!=ordineGiocatoriMano[numGiocateManoCorrente]) {
			try { wait(); } catch (InterruptedException e) { }
		}
	}
}
