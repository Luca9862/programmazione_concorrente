import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class Produttore {
	int numIterations;
	public Produttore(int n){
		numIterations=n;
	}
	public void exec() throws IOException {
		InetAddress addr = InetAddress.getByName(null);
		Socket s = new Socket(addr, 8080);
		ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(s.getInputStream());
		for(int i=0; i<numIterations; i++){
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(300));
			} catch (InterruptedException e) { }
			out.writeObject("produzione");
			Elemento e = new Elemento("el_"+i, ThreadLocalRandom.current().nextInt(300));
			out.writeObject(e);
			//buffer.setItem(new Elemento("el_"+i, ThreadLocalRandom.current().nextInt(300)));
		}
		System.out.println("Produttore: termino");
	}

	public static void main(String[] args) {
		new Produttore(4);
	}
}


