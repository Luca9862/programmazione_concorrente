package soluzioneprof;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Richiedente {
	int mioId;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public Richiedente(int id) {
		mioId=id;
	}
	public void exec() throws IOException, ClassNotFoundException {
		final int numIterazioni=3;
		String richiesta;
		RichiestaRisposta ricrisp;
		InetAddress addr = InetAddress.getByName(null);
		Socket s=new Socket(addr, 8999);
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		for(int i=0; i<numIterazioni; i++) {
			richiesta="Ric. "+i+" da "+mioId;
			int richId=mioId*10000+i;
			ricrisp=new RichiestaRisposta(richId, richiesta);
			System.out.println("Richiedente "+mioId+" inserisco "+richiesta);
			out.writeObject("AggiuntaRichiesta");
			out.writeObject(ricrisp);
			boolean pronto=false;
			while(!pronto) {
				out.writeObject("LetturaRisposta");
				out.writeObject(richId);
				ricrisp=(RichiestaRisposta) in.readObject();
				if(ricrisp!=null) {
					pronto=true;
					System.out.println("Richiedente "+mioId+" inserita "+richiesta);
				} else {
					System.out.println("Richiedente "+mioId+" dormita ");
					try { Thread.sleep(1000); } catch (InterruptedException e) { }
				}
			}
			System.out.println("Richiedente "+mioId+" Q: richiesta, A: "+ricrisp.getRisposta());
		}
		out.writeObject("END");
		System.out.println("Richiedente "+mioId+" termina ");
		s.close();
	}
	public static void main(String[] args) {
		Random rnd=new Random();
		try {
			new Richiedente(rnd.nextInt(999999)).exec();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Richiedente KO");
		}
	}
}
