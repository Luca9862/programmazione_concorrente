package traccia;

public class Deposito {
	private Informazione informazione;
	public Deposito () {
		informazione = new Informazione("default") ;
	}
	public void write(String message) {
		informazione = new Informazione(message) ;
		System.out.println("Deposito: new info is "+ message);
	}
	public Informazione read(long lastTime) throws InterruptedException {
		return informazione;
	}
}
