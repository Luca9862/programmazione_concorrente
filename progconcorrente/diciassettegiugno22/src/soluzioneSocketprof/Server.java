package soluzioneSocketprof;

import java.net.*;
import java.io.*;

public class Server {
	static final int PORT = 8999;
	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		Gioco ilGioco=new Gioco();
		new ServerGioco(ilGioco).start();
		System.out.println("Server inizia");
		try {
			while (true) {
				Socket socket = s.accept();
				System.out.println("Server accepts connection");
				new SlaveThread(socket, ilGioco).start();
			}
		} finally {
			s.close();
		}
	}
}
