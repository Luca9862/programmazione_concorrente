package traccia;

import java.util.concurrent.ThreadLocalRandom;

public class TheMain {
	static final int numClients=4;
	public static void main (String args[])
			throws InterruptedException {
		Deposito depInfo = new Deposito();
		for(int i=0; i<numClients; i++) {
			new Updater(depInfo).start();
			Thread.sleep(ThreadLocalRandom.current().nextInt(30));
			new Client(depInfo).start();
		}
	}
}
