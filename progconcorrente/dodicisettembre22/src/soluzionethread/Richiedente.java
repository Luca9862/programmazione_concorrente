package soluzionethread;

public class Richiedente extends Thread {
	int mioId;
	Deposito depositoIn;
	Deposito depositoOut;
	public Richiedente(int id, Deposito dIn, Deposito dOut) {
		mioId=id;
		depositoIn=dIn;
		depositoOut=dOut;
		start();
	}
	public void run() {
		final int numIterazioni=3;
		String richiesta;
		RichiestaRisposta ricrisp;
		for(int i=0; i<numIterazioni; i++) {
			int richId=mioId*10000+i;
			richiesta="richiesta"+richId;
			ricrisp=new RichiestaRisposta(richId, richiesta);
			System.out.println("Richiedente "+mioId+" inserisco "+richiesta);
			depositoIn.Aggiungi(ricrisp);
			boolean pronto=false;
			while(!pronto) {
				try {
					ricrisp=depositoOut.LeggiIdx(richId);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				if(ricrisp!=null) {
					pronto=true;
				} else {
					System.out.println("Richiedente "+mioId+" dormo ");
					try { Thread.sleep(1000); } catch (InterruptedException e) { }
				}
			}
			System.out.println("Richiedente "+mioId+" Q: "+ricrisp.getRichiesta()+", A: "+ricrisp.getRisposta());
		}
		System.out.println("Richiedente "+mioId+" termina ");
	}
}
