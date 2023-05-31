package traccia;

public class IlMain {
	static final int numGiocatori=4;
	Partita laPartita;
	void exec() {
		laPartita=new Partita(numGiocatori);
		for(int i=0; i<numGiocatori; i++) {
			new Giocatore(i, laPartita);
		}
	}
	public static void main(String[] args) {
		new IlMain().exec();

	}
}

