package traccia;

// soluzione bis
// bloccante la lettura(idx)
public class TheMain {

	final int numRichiedenti=3;
	final int numEsecutori=3;
	Deposito depositoIn = new Deposito();
	Deposito depositoOut = new Deposito();	
	public void exec() {
		for(int i=0; i<numEsecutori; i++) {
			new Esecutore(i, depositoIn, depositoOut);
		}
		for(int i=0; i<numRichiedenti; i++) {
			new Richiedente(i, depositoIn, depositoOut);
		}	
	}
	public static void main(String[] args) {
		new TheMain().exec();
	}
}
