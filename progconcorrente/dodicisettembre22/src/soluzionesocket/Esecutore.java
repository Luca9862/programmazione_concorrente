package soluzionesocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Esecutore extends Thread{
	int mioId;

	Socket socket;
	ObjectInputStream in;
	ObjectOutputStream out;
	public Esecutore(int id) {
		mioId=id;
	}
	public void run() {
		final int numIterazioni=3;
		String risposta;
		RichiestaRisposta ricrisp=null;
		InetAddress addr;
		try{
			addr = InetAddress.getByName(null);
			socket = new Socket(addr, 8080);
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		for(int i=0; i<numIterazioni; i++) {
			boolean pronto=false;
			while(!pronto) {
				try {
					out.writeObject("LetturaRichiesta");
					ricrisp=(RichiestaRisposta) in.readObject();
				} catch (IOException | ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
				if(ricrisp!=null) {
					pronto=true;
				} else {
					System.out.println("Esecutore "+mioId+" dormo ");
					try { Thread.sleep(1000); } catch (InterruptedException e) { }
				}
			}				
			// System.out.println("Esecutore "+mioId+" estratto");
			try { Thread.sleep(500); } catch (InterruptedException e) { }
			risposta="risposta a "+ricrisp.getRichiesta();
			ricrisp.setRisposta(risposta);
			try {
				out.writeObject("AggiuntaRisposta");
				out.writeObject(ricrisp);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			System.out.println("Esecutore "+mioId+" Q: "+ricrisp.getRichiesta()+", A: "+risposta);
		}
		System.out.println("Esecutore "+mioId+" termina ");
		try {
			socket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		Random rnd=new Random();
		try {
			new soluzioneprof.Esecutore(rnd.nextInt(999999)).exec();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Esecutore KO");
		}
	}
}
