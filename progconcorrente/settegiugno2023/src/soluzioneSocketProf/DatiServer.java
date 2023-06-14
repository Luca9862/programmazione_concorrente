package soluzioneSocketProf;

import java.io.*;
import java.net.*;

public class DatiServer {

	public static final int PORT = 2345;

	public static void main(String[] args) {
		Dati iDati = new Dati();
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(PORT);
			System.out.println("Server ready");
			while(true) {
				Socket cliSocket = ss.accept();
				new Thread(new ServerThread(cliSocket, iDati)).start();
				//cliSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
