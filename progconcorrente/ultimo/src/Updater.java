import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

class Updater {
	String name ;
	Socket s;
	ObjectInputStream in;
	ObjectOutputStream out;
	Updater(int n) {
		this.name="Updater_" + n;
	}
	public void exec() throws IOException{
		InetAddress addr = InetAddress.getByName(null);
		this.s = new Socket(addr, 8080);
		in = new ObjectInputStream(s.getInputStream());
		out = new ObjectOutputStream(s.getOutputStream());
		out.writeObject("write");

		for(int j=0; j<10; j++) {
			try {
				Thread.sleep(ThreadLocalRandom.current().nextInt(500));
			} catch (InterruptedException e) { }
			
			out.writeObject(j);
				//deposito.write(name+"_"+j);
		}
		out.writeObject("END");
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		for(int i = 0; i<4; i++) {
			new Updater(i).exec();
		}
	}
}
