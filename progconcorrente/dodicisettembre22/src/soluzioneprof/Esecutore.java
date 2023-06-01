package soluzioneprof;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Esecutore {
	int mioId;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public Esecutore(int id) {
		mioId=id;
	}
	public void exec() throws IOException, ClassNotFoundException {
		final int numIterazioni=3;
		String risposta;
		RichiestaRisposta ricrisp=null;
		InetAddress addr = InetAddress.getByName(null);
		Socket s=new Socket(addr, 8999);
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		for(int i=0; i<numIterazioni; i++) {
			boolean pronto=false;
			while(!pronto) {
				out.writeObject("LetturaRichiesta");
				ricrisp=(RichiestaRisposta) in.readObject();
				if(ricrisp==null) {
					try { Thread.sleep(1000); } catch (InterruptedException e) { }
					System.out.println("Esecutore "+mioId+" dormita");
				} else {
					System.out.println("Esecutore "+mioId+" estratto");
					pronto=true;
				}
			}
			try { Thread.sleep(500); } catch (InterruptedException e) { }
			risposta="risposta a "+ricrisp.getRichiesta();
			ricrisp.setRisposta(risposta);
			out.writeObject("AggiuntaRisposta");
			out.writeObject(ricrisp);
			System.out.println("Esecutore "+mioId+" Q: richiesta, A: "+risposta);
		}
		out.writeObject("END");
		System.out.println("Esecutore "+mioId+" termina ");
		s.close();
	}
	public static void main(String[] args) {
		Random rnd=new Random();
		try {
			new Esecutore(rnd.nextInt(999999)).exec();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Esecutore KO");
		}
	}
}
