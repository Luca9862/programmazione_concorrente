package soluzionesocketProf;

import java.net.*;
import java.io.*;

public class Server {
	public static int numGiocatori=4;
	static final int PORT = 8999;
	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		Partita laPartita=new Partita(numGiocatori);
		System.out.println("Server inizia");
		try {
			while (true) {
				Socket socket = s.accept();
				System.out.println("Server accepts connection");
				new SlaveThread(socket, laPartita).start();
			}
		} finally {
			s.close();
		}
	}
}
