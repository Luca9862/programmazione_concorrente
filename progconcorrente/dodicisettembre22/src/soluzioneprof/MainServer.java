package soluzioneprof;

import java.net.*;
import java.io.*;

public class MainServer {
	static final int PORT = 8999;
	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		Deposito depIn=new Deposito();
		Deposito depOut=new Deposito();
		System.out.println("Server Started");
		try {
			while (true) {
				Socket socket = s.accept();
				System.out.println("Server accepted connection");
				new DepositoServerThread(socket, depIn, depOut);
			}
		} finally {
			s.close();
		}
	}
}
