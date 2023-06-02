package soluzionesocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Produttore {
	int numIterations;

	Random rnd;
	ObjectOutputStream out;
	ObjectInputStream in;
	public Produttore(int n){
		rnd = new Random();
		numIterations=n;
	}
	public void exec() throws IOException {
		InetAddress addr = InetAddress.getByName(null);
		Socket s = new Socket(addr, 8080);
		in = new ObjectInputStream(s.getInputStream());
		out = new ObjectOutputStream(s.getOutputStream());
		for(int i=0; i<numIterations; i++){
			out.writeObject("Produzione");
			Elemento elemento = new Elemento("prod " + i, rnd.nextInt());
			out.writeObject(elemento);
			try {
				Thread.sleep(rnd.nextInt(2000));
			} catch (InterruptedException e) { }
			//buffer.setItem(new Elemento("el_"+i, ThreadLocalRandom.current().nextInt(300)));
		}
		System.out.println("Produttore: termino");
		s.close();
	}

	public static void main(String[] args) {
		try {
			new Produttore(4).exec();
		} catch (IOException e) {
			System.out.println("Consumatore KO");
		}
	}
}


