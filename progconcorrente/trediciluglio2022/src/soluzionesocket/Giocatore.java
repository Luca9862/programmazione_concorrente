package soluzionesocket;

/*import traccia.SituazionePartita;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLOutput;
import java.util.Random;

public class Giocatore {
	int mioId;
	Random rnd;

	ObjectInputStream in;
	ObjectOutputStream out;

	public Giocatore(int id) {
		mioId = id;
		rnd = new Random();
	}

	public void exec() throws IOException, ClassNotFoundException {
		System.out.println("CIAO");
		int numMani;
		int miaMossa;
		InetAddress add = InetAddress.getByName(null);
		System.out.println(1);
		Socket socket = new Socket(add, 8089);
		System.out.println(2);
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
		System.out.println(3);

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
			out.writeObject("giocata");
			out.writeObject(mioId);
		}

		out.writeObject("END");
		System.out.println("Il giocatore" + mioId + "ha terminato");
		out.flush();
		out.close();
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("GAY");
		for(int i = 0; i<4; i++){
			new Giocatore(i).exec();
		}
	}
}*/

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;

public class Giocatore{
	Partita laPartita;
	int mioId;
	Random rnd;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public Giocatore(int id) {
		mioId = id;
		rnd=new Random();
	}
	public void exec() throws IOException, ClassNotFoundException {
		//int numMani=laPartita.numMani();
		int numMani;
		int miaMossa;
		//creo il canale di comunicazione
		SituazionePartita situazionePartita;
		InetAddress addr = InetAddress.getByName(null);
		Socket s=new Socket(addr, 8999);
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		System.out.println("inizio");
		out.writeObject("numMani");
		numMani = (int) in.readObject();
		System.out.println("Giocatore "+mioId+": inizio");
		for(int j=1; j<numMani+1; j++) {
			System.out.println("Giocatore "+mioId+ " mano " + j +" : aspetto");
			out.writeObject("aspetta");
			out.writeObject(mioId);
			//questo l'ho messo - laPartita.aspettaTurno(mioId);
			situazionePartita = (SituazionePartita) in.readObject();
			//SituazionePartita statoPartita=laPartita.leggiSituazione();
			// la mossa dovrebbe tenere conto della situazione, ma semplifichiamo...
			miaMossa=1+rnd.nextInt(9);

			System.out.println("Giocatore "+mioId+": gioco "+miaMossa + " mano " + j);
			out.writeObject("gioca");
			out.writeObject(mioId);
			//laPartita.giocata(mioId);
		}
		out.writeObject("END");
		System.out.println("Il giocatore " + mioId + " ha terminato");
		out.flush();
		s.close();
	}
	public static void main(String[] args) {
		for(int i = 0; i<4;i++){
			try {
				System.out.println("ho creato");
				new Giocatore(i).exec();
			} catch (ClassNotFoundException | IOException e) {
				System.out.println("Consumatore KO");
			}
		}
	}
}
