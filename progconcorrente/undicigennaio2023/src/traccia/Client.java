package traccia;

import java.util.Random;

public class Client extends Thread {
	Deposito deposito;
	int theID;
	Random rnd;
	public Client(int i, Deposito d) {
		deposito=d;
		theID=i;
		rnd=new Random();
		this.start();
	}

	public void run() {
		while(true) {
			boolean temp=rnd.nextBoolean();
			deposito.switchElement(temp);
		}
	}
}
