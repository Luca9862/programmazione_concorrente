import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ThreadLocalRandom;

public class Consumatore {
	Elemento v;
	int numIterations;
	public Consumatore(int n){
		numIterations=n;
	}
	public void exec() throws IOException {
		InetAddress addr = InetAddress.getByName(null);
		Socket s = new Socket(addr, 8080);
		ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(s.getInputStream());
		for(int i=0; i<numIterations; i++){
			try {
				out.writeObject("consumazione");
				Elemento e = (Elemento) in.readObject();

				//v=buffer.getItem();
			} catch (IOException | ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(300));
			} catch (InterruptedException e) { }
		}
		System.out.println("consumatore: termino");
	}

	public static void main(String[] args) {
		new Consumatore(4);
	}
}

