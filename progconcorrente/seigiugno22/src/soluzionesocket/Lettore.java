package soluzionesocket;

import java.util.concurrent.ThreadLocalRandom;

public class Lettore extends Thread {
	Elemento v;
	int numIterations;
	public Lettore(String s, int n){
		super(s);
		numIterations=n;
	}
	public void run(){
		for(int i=0; i<numIterations; i++){

			//v=buffer.readItem();

			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(300));
			} catch (InterruptedException e) { }
		}
		System.out.println(this.getName()+": termino");
	}
}

