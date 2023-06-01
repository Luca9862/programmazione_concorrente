package soluzioneprof;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DepositoServerThread extends Thread {
	Deposito depositoIn;
	Deposito depositoOut;
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	protected DepositoServerThread(Socket s, Deposito dIn, Deposito dOut) throws IOException {
		socket=s;
		depositoIn = dIn;
		depositoOut = dOut;
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		start();
	}
	private void exec(String s) throws ClassNotFoundException, IOException {
		RichiestaRisposta rr;
		if(s.equals("LetturaRichiesta")) {
			rr=depositoIn.LeggiQualunque();
			try {
				out.writeObject(rr);
			} catch (IOException e1) {
				System.err.println("server: problem with "+s);
				e1.printStackTrace();
			}
		}
		if(s.equals("LetturaRisposta")) {
			int idx=(int) in.readObject();
			rr=depositoOut.LeggiIdx(idx);
			try {
				out.writeObject(rr);
			} catch (IOException e1) {
				System.err.println("server: problem with "+s);
				e1.printStackTrace();
			}
		}
		if(s.equals("AggiuntaRichiesta")) {
			try {
				rr = (RichiestaRisposta) in.readObject();
				depositoIn.Aggiungi(rr);
			} catch (ClassNotFoundException | IOException e1) {
				System.err.println("server: problem with "+s);
				e1.printStackTrace();
			}
		}
		if(s.equals("AggiuntaRisposta")) {
			try {
				rr = (RichiestaRisposta) in.readObject();
				depositoOut.Aggiungi(rr);
			} catch (ClassNotFoundException | IOException e1) {
				System.err.println("server: problem with "+s);
				e1.printStackTrace();
			}
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
					System.out.println("executing: " + str);
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
