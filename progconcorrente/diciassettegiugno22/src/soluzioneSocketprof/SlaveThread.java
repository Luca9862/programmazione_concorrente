package soluzioneSocketprof;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SlaveThread extends Thread{
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;	
	Gioco ilGioco;
	public SlaveThread(Socket s, Gioco g) throws IOException {
		socket = s;
		ilGioco=g;
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
	}
	private void exec(String comando) throws ClassNotFoundException, IOException {
		DatoScommessa scommessa;
		boolean esito;
		if(comando.equals("piazzaScommessa")) {
			scommessa=(DatoScommessa) in.readObject();
			// System.out.println("slave ha ricevuto: " + scommessa);
			esito=ilGioco.piazzaScommessa(scommessa);
			// qui bisognerebbe memorizzare la scommessa, ma lasciamo perdere
			out.writeObject(esito);
		} else if (comando.equals("leggiEstratto")) {
			out.writeObject(ilGioco.leggiEstratto());
		} else {
			out.writeObject("comando sconosciuto");
		}
	}
	public void run() {
		boolean finito=false;
		String str=" ";
		try {
			while (!finito) {
				str = (String) in.readObject();
				if (str.equals("END")) {
					finito=true;
				} else {
					System.out.println("slave esegue: " + str);
					exec(str);
				}
			}
			System.out.println("closing...");
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("IO Exception on "+str);
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Socket not closed");
			}
		}
	}
}
