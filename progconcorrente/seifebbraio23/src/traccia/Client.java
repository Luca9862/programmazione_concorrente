package traccia;

import java.util.concurrent.ThreadLocalRandom;

class Client extends Thread {
	String name ;
	Deposito deposito;
	Client(Deposito d) {
		this.deposito=d;
		this.name="Client_" + getName();
	}
	public void run(){
		long lastTime=System.currentTimeMillis()-1000000;
		for(int j=0; j<10; j++) {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(300));
			} catch (InterruptedException e) { }
			Informazione lettura;
			try {
				lettura = deposito.read(lastTime);
				System.out.println(name+" leggo "+lettura);
				lastTime=lettura.getInfoTime();
			} catch (InterruptedException e) {	}
		}
	}
}
