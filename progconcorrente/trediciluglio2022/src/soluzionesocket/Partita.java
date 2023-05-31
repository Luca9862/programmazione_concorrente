package soluzionesocket;

import java.io.Serializable;
import java.util.Random;

public class Partita implements Serializable {
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
	private synchronized void riordinaGiocatori() {
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
		for(int j = 0; j < numGiocatori; j++){
			System.out.print(ordineGiocatoriMano[j]);
		}
	}
	private synchronized void reset() {
		numGiocateManoCorrente=0;
		riordinaGiocatori();
	}
	public synchronized int numMani() {
		return numMani;
	}
	public SituazionePartita leggiSituazione() {
		return statoPartita;
	}
	public synchronized boolean giocata(int idGiocatore, int x) {
		if(idGiocatore == ordineGiocatoriMano[numGiocateManoCorrente]){
			statoPartita.aggiornaSituazione("boh");
			numGiocateManoCorrente++;
			if(numGiocateManoCorrente>=numGiocatori) {
				reset();
			}
			notifyAll();
			return true;
		}
		else
			return false;
	}
	public synchronized void aspettaTurno(int idGiocatore) throws InterruptedException {
		while(idGiocatore != ordineGiocatoriMano[numGiocateManoCorrente])
			wait();
	}
}
