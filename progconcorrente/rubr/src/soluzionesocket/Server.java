package soluzionesocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static final int PORT = 2345;

	public static void main(String[] args) {
		Rubrica miaRubrica = new Rubrica();
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(PORT);
			System.out.println("Server ready");
			while(true) {
				Socket cliSocket = ss.accept();
				System.out.println("SERVER CONNESSO");
				new Thread(new ServerThread(cliSocket, miaRubrica)).start();
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
