package soluzionesocketProf;

import java.io.*;
import java.net.Socket;

public class SlaveThread extends Thread {
	Partita laPartita;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	protected SlaveThread(Socket s, Partita p) throws IOException  {
		socket = s;
		laPartita=p;
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
	}
	private void exec(String comando) throws ClassNotFoundException, IOException {
		int id=-99999;
		if(comando.equals("aspettaTurno")) {
			id= (int) in.readObject();
			laPartita.aspettaTurno(id);
			out.writeObject(laPartita.leggiSituazione());
		} else if (comando.equals("numMani")) {
			out.writeObject(laPartita.numMani());
		} else if (comando.equals("giocata")) {
			int mossa=(int)in.readObject();
			// bisognerebbe controllate che id sia significativo...
			laPartita.giocata(id, mossa);
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
