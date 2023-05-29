package soluzionesocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread implements Runnable{

	Socket cliSocket;
	Rubrica miaRubrica;
	ObjectOutputStream oos;
	ObjectInputStream ois;

	public ServerThread(Socket s, Rubrica r) {
		cliSocket = s;
		miaRubrica = r;
		try {
			oos = new ObjectOutputStream(cliSocket.getOutputStream());
			ois = new ObjectInputStream(cliSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		String str = null;
		Numero num = null;
		String chi = null;
		boolean inRub = false; 
		System.out.println("Server thread running");
		while(true) {
			try {
				str = (String)ois.readObject();
				if(str.equals("FineServizio")) {
					break;
				}
				if(str.equals("Aggiungi")) {
					num = (Numero)ois.readObject();
					miaRubrica.aggiungiNumero(num.getChi(), num.getNum());
				}
				else if(str.equals("Trova")) {
					str = (String)ois.readObject();
					chi = miaRubrica.trova(str);
					oos.writeObject(chi);
				}
				else if(str.equals("Elimina")) {
					str = (String)ois.readObject();
					miaRubrica.eliminaNumero(str);
				}
				else if(str.equals("InRubrica")) {
					str = (String)ois.readObject();
					inRub = miaRubrica.inRubrica(str);
					oos.writeObject(inRub);
				}
			} catch (ClassNotFoundException | IOException e) {
				break;
			} 
		}
		try {
			System.out.println("Server socket completes");
			cliSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
