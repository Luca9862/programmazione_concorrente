package traccia;

import java.util.concurrent.ThreadLocalRandom;

class Updater extends Thread {
	String name ;
	Deposito deposito;
	Updater(Deposito d) {
		this.deposito=d;
		this.name="Updater_" + getName();
	}
	public void run(){
		for(int j=0; j<10; j++) {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(500));
			} catch (InterruptedException e) { }
				deposito.write(name+"_"+j);
		}
	}
}
