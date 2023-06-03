import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket socket;
	Deposito deposito;
	ObjectInputStream in;
	ObjectOutputStream out;
	
	public ServerThread(Socket s, Deposito d) throws IOException {
		this.socket = s;
		this.deposito = d;
		in = new ObjectInputStream(s.getInputStream());
		out = new ObjectOutputStream(s.getOutputStream());
		start();
	}
	
	private void exec(String comando) throws ClassNotFoundException, IOException, InterruptedException{
		Informazione informazione;
		if(comando.equals("write")) {
			informazione = (Informazione) in.readObject();
			this.deposito.write("Updater_" + getName());
		}
		
		if(comando.equals("read")){
			informazione = this.deposito.read(System.currentTimeMillis()-1000000);
			out.writeObject(informazione);
		}
			
	}
	
	public void run() {
		boolean finito = false;
		String str="";
		while(!finito){
			try {
				str = (String) in.readObject();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(str.equals("END"))
				finito = true;
			else {
				try {
					exec(str);
				} catch (ClassNotFoundException | IOException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
