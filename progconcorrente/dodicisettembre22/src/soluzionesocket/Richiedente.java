package soluzionesocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class Richiedente extends Thread {
	int mioId;
	Socket socket;
	ObjectInputStream in;
	ObjectOutputStream out;
	public Richiedente(int id) {
		mioId=id;
	}
	public void exec() {
		final int numIterazioni=3;
		String richiesta;
		RichiestaRisposta ricrisp;
		InetAddress addr = null;
		try {
			addr = InetAddress.getByName(null);
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
		try{
			socket = new Socket(addr, 8080);
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		for(int i=0; i<numIterazioni; i++) {
			int richId=mioId*10000+i;
			richiesta="richiesta"+richId;
			ricrisp=new RichiestaRisposta(richId, richiesta);
			System.out.println("Richiedente "+mioId+" inserisco "+richiesta);
			try {
				out.writeObject("AggiuntaRichiesta");
				out.writeObject(richiesta);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			//depositoIn.Aggiungi(ricrisp);
			boolean pronto=false;
			while(!pronto) {
				try {
					out.writeObject("LetturaRisposta");
					out.writeObject(richId);
					ricrisp = (RichiestaRisposta) in.readObject();
					//ricrisp=depositoOut.LeggiIdx(richId);
				} catch (ClassNotFoundException | IOException e) {
					throw new RuntimeException(e);
				}
				if(ricrisp!=null) {
					pronto=true;
				} else {
					System.out.println("Richiedente "+mioId+" dormo ");
					try { Thread.sleep(1000); } catch (InterruptedException e) { }
				}
			}
			System.out.println("Richiedente "+mioId+" Q: "+ricrisp.getRichiesta()+", A: "+ricrisp.getRisposta());
		}
		System.out.println("Richiedente "+mioId+" termina ");
	}

	public static void main(String[] args) {
		Random rnd=new Random();
		new Richiedente(rnd.nextInt(999999)).exec();
	}
}
