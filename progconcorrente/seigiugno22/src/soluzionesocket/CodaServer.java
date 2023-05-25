package soluzionesocket;

import java.net.*;
import java.io.*;

public class CodaServer {
	static final int PORT = 8999;
	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		Coda laCoda=new Coda(8);
		System.out.println("Server Started");
		try {
			while (true) {
				Socket socket = s.accept();
				System.out.println("Server accepts connection");
				new CodaServerThread(socket, laCoda);
			}
		} finally {
			s.close();
		}
	}
}
