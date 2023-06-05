package soluzioneSocketprof;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Giocatore {
	private Random rnd;
	private int mioId;
	Giocatore(){
		rnd=new Random();
		mioId=rnd.nextInt(1000);
	}
	private void exec() throws IOException, ClassNotFoundException {
		int estratto;
		DatoScommessa miaScommessa;
		InetAddress addr = InetAddress.getByName(null);
		Socket s=new Socket(addr, Server.PORT);
		ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(s.getInputStream());
		int numIterations=2+rnd.nextInt(8);
		System.out.println("giocatore_"+mioId+": inizio a giocare per "+numIterations+" volte");
		for(int i=0; i<numIterations; i++) {
			miaScommessa=new DatoScommessa(rnd.nextInt(Gioco.MAX_NUM), rnd.nextInt(10));
			boolean fatto=false;
			while(!fatto) {
				out.writeObject("piazzaScommessa");
				out.writeObject(miaScommessa);
				fatto= (boolean) in.readObject();
				if(!fatto) {
					try { Thread.sleep(250); } catch (InterruptedException e) { }
				}
			}
			System.out.println("giocatore_"+mioId+": faccio "+miaScommessa);
			out.writeObject("leggiEstratto");
			estratto=(int) in.readObject();
			if(estratto==miaScommessa.getNumero()) {
				System.out.println("giocatore_"+mioId+": ho vinto!");
			} else {
				System.out.println("giocatore_"+mioId+": ho perso!");
			}
		}
		System.out.println("giocatore_"+mioId+": smetto");
		out.writeObject("END");
		out.flush();
		s.close();
	}
	public static void main(String[] args) {
		try {
			new Giocatore().exec();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Giocato termina con problemi");
		} 
	}
}
