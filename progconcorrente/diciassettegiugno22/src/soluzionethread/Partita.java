package soluzionethread;
import java.util.Random;

public class Partita {
	static final int NUM_MANI=4;
	private Random rnd;
	private int[] giocate;
	private int numGiocate;
	private int numGiocatori;
	private int totaleMano;
	Partita(int n){
		numGiocatori=n;
		giocate= new int[numGiocatori];
		reset();
		rnd=new Random();
	}
	// resetta variabili: chiamata all'inizio di ogni mano
	public void reset() {
		System.out.println("Partita: reset");
		numGiocate=0;
		for(int i=0; i<numGiocatori; i++) {
			giocate[i]=-1;
		}
	}
	// dice da quante mani e` costituita la partita
	public int numMani() {
		return NUM_MANI;
	}
	// calcola punti complessivamente fatti dai giocatori nella mano
	private void calcola() {
		totaleMano=0;
		for(int n: giocate) {
			totaleMano+=n;
		}
	}
	// mostra le giocate di tutti i giocatori. Chiamabile alla fine di ogni mano
	private void mostraGiocate() {
		System.out.print("[ ");
		for(int n: giocate) {
			System.out.print(n+" ");
		}
		System.out.println("]");
	}
	//  con questo metodo un giocatore fa la sua giocata
	public void giocata(int idGiocatore, int carta) {
		if(giocate[idGiocatore]<0) {
			giocate[idGiocatore]=carta;
			System.out.println("Giocatore "+idGiocatore+" ha giocato "+carta);
			numGiocate++;
			if(numGiocate==numGiocatori) { 
				// se tutti i giocatori hanno giocato
				mostraGiocate();
				calcola();
			}
		} 
	}
	// calcola punti fatti dal giocatore id nella mano
	public int puntiMano(int id) {
		int tot=rnd.nextInt(Math.max(1, totaleMano));
		numGiocate--;
		if(numGiocate==0) {
			// quando tutti i giocatori hanno letto il loro punteggio
			// si fa reset per iniziare nuova mano
			reset();
		}
		return tot;
	}
}
