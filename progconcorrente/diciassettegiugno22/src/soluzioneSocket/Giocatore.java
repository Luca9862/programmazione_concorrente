package soluzioneSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Giocatore {
	private Random rnd;
	private int mioId;
	ObjectOutputStream out;
	ObjectInputStream in;
	Giocatore(int id) throws IOException {
		rnd=new Random();
		mioId=id;

	}
	public void exec() throws IOException, ClassNotFoundException {
		InetAddress addr = InetAddress.getByName(null);
		Socket s = new Socket(addr, 8080);
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		int estratto;
		DatoScommessa miaScommessa;
		int numScommesse=2+rnd.nextInt(8);  // numero di scommesse che il giocatore vuol fare
		for(int i=0; i<numScommesse; i++) {
			miaScommessa=new DatoScommessa(rnd.nextInt(4), 1+rnd.nextInt(10));
			boolean fatto=false;
			while(!fatto) {
				try{
					out.writeObject("piazzaScommessa");
					out.writeObject(this.mioId);
					out.writeObject(miaScommessa);
					fatto = (boolean) in.readObject();
					System.out.println(fatto);
					//fatto=ilGioco.piazzaScommessa(mioId, miaScommessa);
				}catch(IOException | ClassNotFoundException e){
					throw new RuntimeException(e);
				}

				if(!fatto) {
					// la scommessa non e` stata accettata
					// aspettiamo un po' e poi riproviamo
					try { Thread.sleep(250); } catch (InterruptedException e) { }
				}
			}
			System.out.println("giocatore_"+mioId+": faccio "+miaScommessa);
			out.writeObject("leggiEstratto");
			//si ferma qua
			estratto = (int) in.readObject();
			System.out.println("giocatore_"+mioId+": estratto="+estratto);
			//estratto=ilGioco.leggiEstratto();
			if(estratto==miaScommessa.getNumero()) {
				System.out.println("giocatore_"+mioId+": ho vinto!");
			} else {
				System.out.println("giocatore_"+mioId+": ho perso!");
			}
		}
		System.out.println("giocatore_"+mioId+": smetto");
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		for(int i = 0; i<4; i++) {
			new Giocatore(i).exec();
		}
	}
}
