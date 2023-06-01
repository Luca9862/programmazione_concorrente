package soluzionesocketProf;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Giocatore {
	Partita laPartita;
	int mioId;
	Random rnd;
	ObjectOutputStream out;
	ObjectInputStream in;
	public Giocatore() {
		rnd=new Random();
	}
	public void exec(String id) throws IOException, ClassNotFoundException {
		laPartita = new Partita(4);
		mioId=Integer.parseInt(id);
		int numMani=laPartita.numMani();
		int miaMossa;
		SituazionePartita statoPartita;
		InetAddress addr = InetAddress.getByName(null);
		Socket mioSocket=new Socket(addr, 5555);
		out = new ObjectOutputStream(mioSocket.getOutputStream());
		in = new ObjectInputStream(mioSocket.getInputStream());
		System.out.println("Giocatore "+mioId+": inizio");
		for(int j=0; j<numMani; j++) {
			System.out.println("Giocatore "+mioId+": aspetto");
			out.writeObject("aspettaTurno");
			out.writeObject(mioId);
			statoPartita = (SituazionePartita) in.readObject();
			// la mossa dovrebbe tenere conto della situazione, ma semplifichiamo...
			miaMossa=1+rnd.nextInt(9);
			System.out.println("Giocatore "+mioId+": gioco "+miaMossa);
			out.writeObject("giocata");
			out.writeObject(miaMossa);
		}
		out.writeObject("END");
	}
	public static void main(String args[]) {
		try {
			for(int i = 0; i<4; i++)
				new Giocatore().exec(String.valueOf(i));
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("giocatore KO");
		}
	}
}
