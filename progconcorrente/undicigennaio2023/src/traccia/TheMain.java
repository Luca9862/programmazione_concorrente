package traccia;

public class TheMain {
	final int numClienti=4;
	Deposito deposito = new Deposito();
	public void exec() {
		for(int i=0; i<numClienti; i++) {
			new Client(i, deposito);
		}
	}
	public static void main(String[] args) {
		new TheMain().exec();
	}

}