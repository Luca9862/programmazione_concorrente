package soluzioneRMIprofB;

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
	private void printWithName(String s, Elemento v) {
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
		printWithName(" estratto ", tmp);
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
		printWithName(" scritto ", v);
		notifyAll();
	}
	public synchronized Elemento readItem(int attesaMax){
		Elemento tmp;
		if(attesaMax<0) {
			while (numItems==0){
				try {
					wait();
				} catch (InterruptedException e) {}
			}			
		} else {
			long t0=System.currentTimeMillis();
			long tFine=t0+1000*attesaMax;
			while (numItems==0 && tFine>t0){
				try {
					wait(tFine-t0);
					t0=System.currentTimeMillis();
				} catch (InterruptedException e) {}
			}
		}
		if(numItems>0) {
			tmp=valori[first];
		} else {
			tmp=new Elemento("boh", -99);
		}
		printWithName(" letto ", tmp);
		return tmp;
	}
	/*
	public synchronized Elemento readItem(int t) {
		Elemento tmp;
		long t0=System.currentTimeMillis();
		while (numItems==0){
			try {
				wait(t);
				if(System.currentTimeMillis()-t0>t) {
					return new Elemento("boh", -1);
				}
			} catch (InterruptedException e) {}
		}
		tmp=valori[first];
		printWithName(" letto ", tmp);
		return tmp;
	}
	*/
}

