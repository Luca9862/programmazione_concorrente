package socket;
/*
    I thread sono client.
    Il server è un thread che si mette in ascolto su una porta e quando riceve una richiesta di connessione
    crea un thread che si occupa di gestire la richiesta.
        1.Tolgo extends Thread dai threads
        2.Elimino start
        3.Modifico il run in exec(String comando)
        4.Tolgo/Commento tutto ciò che riguarda la classe condivisa
        5.Nel costruttore passo un Socket come argomento
        6.Nell'exec creo gli stream ObjectInputStream e ObjectOutputStream passati come parametri della classe
        7.Nell'exec istanzio InetAddress e la passo come argomento al socket
        8.Dove avevamo commentato le parti della classe condivisa, andiamo ad aggiungere il codice necessario ad inviare richieste
            al server e ricevere risposte
        9. out per mandare la richiesta, faccio le operazioni, mando oggetto/ricevo risposta (in questo ultimo caso uso in)
        10.Chiudo il socket [s.close()]
        11.Nel main vado a creare i thread necessari
*/
/*
    esempio di client Produttore e Consumatore
*/

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Clients {

    public class Produttore {
	int numIterations;
	Random rnd;
	ObjectOutputStream out;
	ObjectInputStream in;
	public Produttore(int n){
		rnd = new Random();
		numIterations=n;
	}
	public void exec() throws IOException, IOException {
		InetAddress addr = InetAddress.getByName(null);
		Socket s = new Socket(addr, 8080);
		in = new ObjectInputStream(s.getInputStream());
		out = new ObjectOutputStream(s.getOutputStream());
		for(int i=0; i<numIterations; i++){
			out.writeObject("Produzione");
			Elemento elemento = new Elemento("prod " + i, rnd.nextInt());
			out.writeObject(elemento);
            //buffer.setItem(new Elemento("el_"+i, ThreadLocalRandom.current().nextInt(300)));
            try {
				Thread.sleep(rnd.nextInt(2000));
			} catch (InterruptedException e) { }
		}
		System.out.println("Produttore: termino");
        out.writeObject("END");
        out.flush();
		s.close();
	}

	public static void main(String[] args) {
		try {
			new Produttore(4).exec();
		} catch (IOException e) {
			System.out.println("Consumatore KO");
		}
	}
}

    public class Consumatore {

        Elemento v;
        int numIterations;

        ObjectOutputStream out;
        ObjectInputStream in;


        public Consumatore(int n){
            numIterations=n;
        }
        public void exec() throws IOException, ClassNotFoundException {
            Elemento e;
            InetAddress addr = InetAddress.getByName(null);
            Socket s = new Socket(addr, 8080);
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
            for(int i=0; i<numIterations; i++){
                out.writeObject("Consumazione");
                e = (Elemento) in.readObject();
                //v=buffer.getItem();
                System.out.println("Consumato: " + e);
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(300));
                } catch (InterruptedException ie) { }
            }
            out.writeObject("END");
            out.flush();
            s.close();
            System.out.println("Consumatore: termino");
        }

        public static void main(String[] args) {
            try {
                new Consumatore(4).exec();
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("Consumatore KO");
            }
        }
    }

}
