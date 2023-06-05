package soluzioneSocketprof;

public class ServerGioco extends Thread {
	Gioco ilGioco;
	ServerGioco(Gioco g){
		ilGioco=g;
	}
	public void run() {
		for(;;) {
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
}
