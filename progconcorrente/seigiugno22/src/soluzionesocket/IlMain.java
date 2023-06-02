package soluzionesocket;

import traccia.Coda;
import traccia.Consumatore;
import traccia.Lettore;
import traccia.Produttore;

public class IlMain {
	private static final int bufsize=4;          // dimensioni coda
	private static final int numIterations=8;    // numero iterazioni compiute dai thread
	private static final int numConsumatori=2;   // numero di consumatori 
	private static final int numProduttori=2;    // numero di produttori
	private static final int numLettori=2;       // numero di lettori
	private void exec() {
		traccia.Produttore[] iProduttori=new traccia.Produttore[numProduttori];
		traccia.Consumatore[] iConsumatori=new traccia.Consumatore[numConsumatori];
		traccia.Lettore[] iLettori=new traccia.Lettore[numLettori];
		traccia.Coda buffer=new Coda(bufsize);
		for(int j=0; j<numProduttori; j++) {
			iProduttori[j]=new Produttore("prod_"+j, buffer, numIterations);
		}
		for(int j=0; j<numConsumatori; j++) {
			iConsumatori[j]=new Consumatore("cons_"+j, buffer, numIterations);
		}
		for(int j=0; j<numLettori; j++) {
			iLettori[j]=new Lettore("lett_"+j, buffer, numIterations);
		}
		for(int j=0; j<numProduttori; j++) {
			iProduttori[j].start();
		}
		for(int j=0; j<numConsumatori; j++) {
			iConsumatori[j].start();
		}
		for(int j=0; j<numLettori; j++) {
			iLettori[j].start();
		}
		try {
			for(int j=0; j<numProduttori; j++) {
				iProduttori[j].join();
			}
			for(int j=0; j<numConsumatori; j++) {
				iConsumatori[j].join();
			}
		} catch (InterruptedException e) { }
		System.out.println("main: termino");
		System.exit(0); // esce, terminando eventuali lettori ancora attivi.
	}
	public static void main(String[] args) {
		new IlMain().exec();
	}
}