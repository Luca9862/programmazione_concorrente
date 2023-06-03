import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DepositoServer{
	
	public static final int PORT = 8080;
	public static void main(String[] args) throws IOException {
		Deposito deposito = new Deposito();
		ServerSocket ss = new ServerSocket(PORT);
		System.out.println("SERVER PRONTO");
		try {
			while(true) {
				Socket s = ss.accept();
				System.out.println("SERVER CONNESSO");
				new ServerThread(s, deposito);
			}
		}finally {
			ss.close();
		}
		
	}
}
