package soluzionesocket;

import traccia.SituazionePartita;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Giocatore {
	int mioId;
	Random rnd;

	public Giocatore(int id) {
		mioId = id;
		rnd = new Random();
	}

	public void exec(int idGiocatore) throws IOException, ClassNotFoundException {
		int numMani;
		mioId = idGiocatore;
		int miaMossa;
		InetAddress add = InetAddress.getByName(null);
		int port = 8080;
		Socket socket = new Socket(add, port);
		ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
		ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
		out.writeObject("numMani");
		numMani = (int) in.readObject();
		System.out.println("Giocatore " + mioId + ": inizio");
		for (int j = 0; j < numMani; j++) {
			System.out.println("Giocatore " + mioId + ": aspetto");
			//laPartita.aspettaTurno(mioId);
			out.writeObject("aspetta");
			out.writeObject(mioId);
			//SituazionePartita statoPartita=laPartita.leggiSituazione();
			// la mossa dovrebbe tenere conto della situazione, ma semplifichiamo...
			miaMossa = 1 + rnd.nextInt(9);
			System.out.println("Giocatore " + mioId + ": gioco " + miaMossa);
			//laPartita.giocata(mioId, miaMossa);
			out.writeObject("gioca");
			out.writeObject(mioId);
		}

		out.writeObject("END");
		System.out.println("Il giocatore" + mioId + "ha terminato");
		out.flush();
		out.close();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		for(int i = 0; i<4; i++){
			new Giocatore(i).exec(i);
		}
	}
}
