package soluzionesocket;

import java.util.concurrent.ThreadLocalRandom;

public class Produttore extends Thread {
	Coda buffer;
	int numIterations;
	public Produttore(String s, Coda c, int n){
		super(s);
		this.buffer=c;
		numIterations=n;
	}
	public void run(){
		for(int i=0; i<numIterations; i++){
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(300));
			} catch (InterruptedException e) { }
			try {
				buffer.setItem(new Elemento("el_"+i, ThreadLocalRandom.current().nextInt(300)));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		System.out.println(this.getName()+": termino");
	}
}


