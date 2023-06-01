package soluzionesocket;

public class Coda {
	private int BUFFERSIZE;
	private int numItems = 0;
	private Elemento[] valori;
	private int first;  // indice elemento in testa alla coda
	private int last;   // indice ultimo elemento inserito nella coda
	public Coda(int bufsize) {
		BUFFERSIZE=bufsize;
		first=0;
		last=0;
		valori=new Elemento[BUFFERSIZE];
	}
	// stampa messaggio dato, preceduto dal nome del thread
	void printWithName(String s, Elemento v) {
		System.out.println(Thread.currentThread().getName()+s+v+"["+numItems+"]");
	}
	// legge elemento estraendolo dalla testa della coda
	public synchronized Elemento getItem() throws InterruptedException {
		Elemento tmp;
		while(numItems==0){
			wait();
		}
		numItems--;
		tmp=valori[first];
		first=(first+1)%BUFFERSIZE;
		printWithName(" estratto ", tmp);
		notifyAll();
		return tmp;
	}
	// inserisce elemento in coda
	public synchronized void setItem(Elemento v) throws InterruptedException {
		while(numItems==BUFFERSIZE){
			wait();
		}
		valori[last]=v;
		last=(last+1)%BUFFERSIZE;
		numItems++;
		printWithName(" scritto ", v);
		notifyAll();
	}
	// legge elemento dalla testa della coda, senza fare modifiche alla coda
	public synchronized Elemento readItem() throws InterruptedException {
		Elemento tmp;
		while(numItems==0){
			wait();
		}
		tmp=valori[first];
		printWithName(" letto ", tmp);
		notifyAll();
		return tmp;
	}
}


