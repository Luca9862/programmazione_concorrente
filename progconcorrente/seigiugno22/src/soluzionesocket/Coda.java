package soluzionesocket;

public class Coda {
	private int BUFFERSIZE;
	private int numItems = 0;
	private Elemento[] valori;
	private int first, last; // last is the index of the
	// most recently inserted item
	public Coda(int bufsize) {
		BUFFERSIZE=bufsize;
		first=0;
		last=0;
		valori=new Elemento[BUFFERSIZE];
	}
	void printActionElement(String s, Elemento v) {
		System.out.println(s+v+"["+numItems+"]");		
	}
	public synchronized Elemento getItem(){
		Elemento tmp;
		while (numItems==0){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		numItems--;
		tmp=valori[first];
		first=(first+1)%BUFFERSIZE;
		printActionElement(" estratto ", tmp);
		notifyAll();
		return tmp;
	}
	public synchronized void setItem(Elemento v) {
		while (numItems==BUFFERSIZE){
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		valori[last]=v;
		last=(last+1)%BUFFERSIZE;
		numItems++;
		printActionElement(" scritto ", v);
		notifyAll();
	}
	public synchronized Elemento readItem(int t) {
		Elemento tmp;
		long t0=System.currentTimeMillis();
		long timeToWait=t;
		long elapsed=0;
		while (numItems==0){
			try {
				wait(timeToWait);
				elapsed=System.currentTimeMillis()-t0;
				if(elapsed>=t) {
					return new Elemento("boh", -1);
				}
				timeToWait=t-elapsed;
			} catch (InterruptedException e) {}
		}
		tmp=valori[first];
		printActionElement(" letto ", tmp);
		return tmp;
	}
}

