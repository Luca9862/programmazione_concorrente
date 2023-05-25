package soluzionesocket;

import java.io.*;
import java.net.*;
import java.util.Random;

public class Produttore {
	Elemento v;
	int numIterations;
	Random rnd;
	String name;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	public Produttore(int n){
		numIterations=n;
		rnd=new Random();
		name="prod"+rnd.nextInt(1000);
	}
	private void exec() throws IOException, ClassNotFoundException {
		InetAddress addr = InetAddress.getByName(null);
		Socket s=new Socket(addr, 8999);
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		for(int i=0; i<numIterations; i++){
			out.writeObject("set");
			Elemento el= new Elemento(name+i, 1000-i);
			out.writeObject(el);
			System.out.println(name+": put "+el);
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
			new Produttore(4).exec();
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Consumatore KO");
		} 
	}
}

