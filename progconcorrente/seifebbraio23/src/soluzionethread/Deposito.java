package soluzionethread;

public class Deposito {
	private Informazione informazione;
	public Deposito () {

		informazione = new Informazione("default") ;
	}
	public synchronized void write(String message) {
		informazione = new Informazione(message) ;
		System.out.println("Deposito: new info is "+ message);
		notifyAll();
	}
	public synchronized Informazione read(long lastTime) throws InterruptedException {
		while(informazione.getInfoTime()<=lastTime) {
			wait();
		}
		return informazione;
	}
}
