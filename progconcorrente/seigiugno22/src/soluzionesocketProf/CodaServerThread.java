package soluzionesocketProf;

import java.io.*;
import java.net.*;

public class CodaServerThread extends Thread{
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	Coda laCoda;
	public CodaServerThread(Socket s, Coda c) throws IOException {
		socket = s;
		laCoda=c;
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		start();
	}
	private void exec(String s) {
		if(s.equals("get")) {
			Elemento e=laCoda.getItem();
			try {
				out.writeObject(e);
			} catch (IOException e1) {
				System.err.println("server: problem with "+s);
				e1.printStackTrace();
			}
		}
		if(s.equals("set")) {
			Elemento e=null;
			try {
				e = (Elemento) in.readObject();
				laCoda.setItem(e);
			} catch (ClassNotFoundException | IOException e1) {
				System.err.println("server: problem with "+s);
				e1.printStackTrace();
			}
		}
		if(s.equals("read")) {
			Elemento e=laCoda.readItem(2500);
			try {
				out.writeObject(e);
			} catch (IOException e1) {
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