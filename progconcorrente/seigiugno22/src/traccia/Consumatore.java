package traccia;
import java.util.concurrent.ThreadLocalRandom;

public class Consumatore extends Thread {
	Coda buffer;
	Elemento v;
	int numIterations;
	public Consumatore(String s, Coda c, int n){
		super(s);
		buffer=c;
		numIterations=n;
	}
	public void run(){
		for(int i=0; i<numIterations; i++){
			v=buffer.getItem();
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(300));
			} catch (InterruptedException e) { }
		}
		System.out.println(this.getName()+": termino");
	}
}

