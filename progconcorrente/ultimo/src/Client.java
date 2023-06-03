import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

class Client {
	String name ;
	Socket s;
	ObjectInputStream in;
	ObjectOutputStream out;
	Client(int n) {
		this.name="Client_" + n;
	}
	public void exec() throws ClassNotFoundException, IOException{
		InetAddress addr = InetAddress.getByName(null);
		this.s = new Socket(addr, 8080);
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		out.writeObject("read");
		for(int j=0; j<10; j++) {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(300));
			} catch (InterruptedException e) { }
			Informazione lettura;
			lettura = (Informazione) in.readObject();
			//lettura = deposito.read(lastTime);
			System.out.println(name+" leggo "+lettura);
		}
		out.writeObject("END");
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		for(int i = 0; i<4; i++) {
			new Client(i).exec();
		}
	}
}
