package soluzionesocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientThread extends Thread{

	ObjectOutputStream oos;
	ObjectInputStream ois;
	Socket mySocket;
	int myId;
	
	public ClientThread(int n) {
		myId=n;
		try {
			mySocket = new Socket(InetAddress.getByName(null), Server.PORT);
			oos = new ObjectOutputStream(mySocket.getOutputStream());
			ois = new ObjectInputStream(mySocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			oos.writeObject("Aggiungi");
			oos.writeObject(new Numero("Zia Pina", "+390212345678"));
			
			oos.writeObject("Elimina");
			oos.writeObject("Zia Pina");
			
			oos.writeObject("InRubrica");
			oos.writeObject("Zia Pina");
			boolean esito = (boolean)ois.readObject();
			if(esito) {
				System.out.println("Client "+myId+": Zia Pina e` in rubrica");
			}
			else System.out.println("Client "+myId+": Zia Pina non e` in rubrica");
			oos.writeObject("FineServizio");
			mySocket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
