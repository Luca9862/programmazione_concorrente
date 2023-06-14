package soluzioneSocketProf;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread implements Runnable{

	Socket cliSocket;
	Dati iDati;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	public ServerThread(Socket s, Dati r) {
		cliSocket = s;
		iDati = r;
		try {
			oos = new ObjectOutputStream(cliSocket.getOutputStream());
			ois = new ObjectInputStream(cliSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		String str = null;
		String info = null;
		String key = null;
		System.out.println("Server thread running");
		while(true) {
			try {
				str = (String)ois.readObject();
				if(str.equals("FineServizio")) {
					break;
				}
				if(str.equals("Aggiungi")) {
					key = (String) ois.readObject();
					info = (String) ois.readObject();
					iDati.aggiungiDato(key, info);
				}
				else if(str.equals("Trova")) {
					key = (String)ois.readObject();
					info = iDati.trovaDato(key);
					oos.writeObject(info);
				}
				else if(str.equals("Elimina")) {
					key = (String)ois.readObject();
					iDati.eliminaDato(key);
				}
				else if(str.equals("Esiste")) {
					oos.writeObject(iDati.esisteDato(key));
				}
			} catch (ClassNotFoundException | IOException e) {
				break;
			} 
		}
		try {
			System.out.println("Server socket completes");
			cliSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
