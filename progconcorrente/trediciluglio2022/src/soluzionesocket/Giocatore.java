package soluzionesocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Giocatore{
	Partita laPartita;
	int mioId;
	Random rnd;

	public Giocatore(int id) {
		mioId=id;
		rnd=new Random();
	}
	public void run(){
		InetAddress addr = null;
		try {
			addr = InetAddress.getByName(null);
			Socket socket = new Socket(addr, Integer.parseInt("8080"));
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject("Stringa da passare:");
			in.readObject();
			out.writeObject("END");
			out.flush();
			socket.close();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		int numMani=laPartita.numMani();
		int miaMossa;
		System.out.println("Giocatore "+mioId+": inizio");
		for(int j=0; j<numMani; j++) {
			System.out.println("Giocatore "+mioId+": aspetto");
			try {
				laPartita.aspettaTurno(mioId);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			SituazionePartita statoPartita=laPartita.leggiSituazione();
			// la mossa dovrebbe tenere conto della situazione, ma semplifichiamo...
			miaMossa=1+rnd.nextInt(9);
			System.out.println("Giocatore "+mioId+": gioco "+miaMossa);
			laPartita.giocata(mioId, miaMossa);
		}
	}

}
