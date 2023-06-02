package soluzionesocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class Consumatore {

	Elemento v;
	int numIterations;

	ObjectOutputStream out;
	ObjectInputStream in;


	public Consumatore(int n){
		numIterations=n;
	}
	public void exec() throws IOException, ClassNotFoundException {
		Elemento e;
		InetAddress addr = InetAddress.getByName(null);
		Socket s = new Socket(addr, 8080);
		System.out.println("1");
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		System.out.println(2);
		for(int i=0; i<numIterations; i++){
			out.writeObject("Consumazione");
			e = (Elemento) in.readObject();
			//v=buffer.getItem();
			System.out.println("Consumato: " + e);
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(300));
			} catch (InterruptedException ie) { }
		}
		out.writeObject("END");
		out.flush();
		s.close();
		System.out.println("Consumatore: termino");
	}

	public static void main(String[] args) {
		try {
			new Consumatore(4).exec();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Consumatore KO");
		}
	}
}

