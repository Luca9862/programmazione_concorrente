package tracciaSocket;

public class ServerGioco {
	private final int numGiocatori=4;
	private Gioco ilGioco;
	private Giocatore[] iGiocatori;
	ServerGioco(){
		ilGioco=new Gioco();
		iGiocatori=new Giocatore[numGiocatori];
	}
	private void exec() {
		for(int id=0; id<numGiocatori; id++) {
			iGiocatori[id]=new Giocatore(id, ilGioco);
		}
		for(int id=0; id<numGiocatori; id++) {
			iGiocatori[id].start();
		}
		for(int j=0; j<10; j++) {
			ilGioco.aperturaScommesse();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { }
			ilGioco.faiEstrazione();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) { }
		}
	}
	public static void main(String[] args) {
		new ServerGioco().exec();
	}
}
