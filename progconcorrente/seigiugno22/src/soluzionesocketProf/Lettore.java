package soluzionesocketProf;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Lettore {
	Elemento v;
	int numIterations;
	Random rnd;
	String name;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public Lettore(int n){
		numIterations=n;
		rnd=new Random();
		name="lett"+rnd.nextInt(1000);
	}
	private void exec() throws IOException, ClassNotFoundException {
		InetAddress addr = InetAddress.getByName(null);
		Socket s=new Socket(addr, 8999);
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		for(int i=0; i<numIterations; i++){
			out.writeObject("read");
			Elemento el=(Elemento) in.readObject();
			System.out.println(name+": read "+el);
			try {
				Thread.sleep(rnd.nextInt(2000));
			} catch (InterruptedException e) { }
		}
		out.writeObject("END");
		out.flush();
		System.out.println(name+": termino");
		s.close();
	}
	public static void main(String[] args) {
		try {
			new Lettore(4).exec();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Consumatore KO");
		} 
	}
}

