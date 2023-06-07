package es_2;

import java.io.IOException;  
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
 
public class ClienteDati{
	
	int id;
	ObjectOutputStream out;
	ObjectInputStream in;
	
	public ClienteDati(int i) {
		this.id = i;
	}

	public void exec() throws IOException, IOException, ClassNotFoundException {
		InetAddress addr = InetAddress.getByName(null);
		Socket s = new Socket(addr, 8080);
		out = new ObjectOutputStream(s.getOutputStream());
		in = new ObjectInputStream(s.getInputStream());
		
		out.writeObject("aggiungi");
		out.writeObject("potipu");
		out.writeObject("info potipu");
		
		out.writeObject("aggiungi");
		out.writeObject("patagarru");
		out.writeObject("info patagarru");
		
		out.writeObject("aggiungi");
		out.writeObject("svicolone");
		out.writeObject("info svicolone");
		
		out.writeObject("trova");
		out.writeObject("svicolone");
		boolean esiste = (boolean) in.readObject();
        if(esiste) {
            //String num = iDati.trovaDato(chiaveDaCercare);
        	String num = (String) in.readObject();
            System.out.println( "Thread_" + this.id + "Info ricercata " + "e`"+ num);
        } else {
            System.out.println("svicolone "+" non trovato ");
        }
		
		System.out.println("Produttore: termino");
        out.writeObject("END");
        out.flush();
		s.close();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		for(int i = 0; i<4; i++) {
			new ClienteDati(i).exec();
		}
	}
}
