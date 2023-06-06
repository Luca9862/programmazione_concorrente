# programmazione_concorrente

Il progetto è diviso in moduli.
Ogni modulo ha come nome la data della prova.
All'interno di ogni modulo è presente un package con la traccia più altri package con le soluzioni

## Procedure per concorrenza

1. mettere `synchronized` dove necessario ai metodi;
2. di solito si toglie l' `if` e si sostituisce con un while `while` mettendo all'interno un `wait()`;
3. alla fine in base all'esercizio si mette un `notifyAll()`;
4. se l'esercizio richiede che tutti i thread eseguano una esecuzione a testa e si fermino allora si può usare il `CyclicBarrier`.

## Procedure per i socket

1. eliminare il main dell'applicazione concorrente;

2. serializzare la classe risorsa nel seguente modo:

3. implementazione `Serializable`;

4. `public static final long SerialVersionUID = 1L;`

5. i thread sono client: - Tolgo `extends Thread`;
   - `InetAddress addr = InetAddress.getByName(null);`
   - `Socket clientSocket = new Socket(addr, 8080);`
   - `ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());`
   - `ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());`
   - `writeObject` serve per inviare un oggetto.
   - `ReadObject` server per ricevere un oggetto.
   - `out.writeObject(“Stringa da passare”);` **(RICORDA: passare anche l’argomento!)**
   - `in.readObject() ` ricordare di fare il cast con il tipo o oggetto utilizzato.
   - Alla fine di tutto fare:
     - `out.writeObject(“END”);`
     - `out.flush();`
     - `ClientSocket.close();`
6. Si crea il main della classe:
   - `new nomeclasse(999);` oppure fare `Random`.

```java
import java.io.IOException;

public class ServerMain {
    public static final int PORT = 8080;
    public static int numGiocatori = 4;
    public static void main (String[] args) throws IOException {
        ServerSocket Server = new ServerSocket(PORT);
        Partita laPartita = new Partita(numGiocatori);

        try {
            while (true) {
                Socket ClientSocket = Server.accept();
                new Serverthread(ClientSocket, laPartita)
            }
        } finally {
            Server.close();
        }
    }
}
```

7. Creo classe ServerThread:

- Come prima cosa metto extends Thread.
- Dichiaro `ObjectInputStream` e `ObjectOutputStream`
- Dichiaro la classe condivisa
- Dichiaro: Socket `clientSocket`;
- Dichiaro il costruttore nel seguente modo:
  - public ServerThread(`Socket s`, `classeCondivisa oggettoCondiviso`){
    - faccio tutte le assegnazioni del caso ed istanzio `ObjectInput` e `Output Stream`:
    - Alla fine faccio: `start()`;
- Creo:

```java
private void exec(String str) throws IOException, ClassNotFoundException {
	int id = 0;
	if(str == "aspettaTurno"){
		id = (int)in.readObject();
		laPartita.aspettaTurno(id);
		out.writeObject(laPartita.leggiSituazione());
	}

	if(str == "giocata") {
		int mossa = (int)in.readObject();
		laPartita.giocata(id, mossa);
	}

	if(str == "numMani") {
		out.writeObject(laPartita.numMani);
	}
}
```

```java
    public void run() {
	boolean finito = false;
	String str = "";

	try {
		while (!finito) {
			str = (String)in.readObject();
			if(str == "END") {
				finito = true;
			} else {
				System.out.println("Slave esegue: " + str);
				exec(str);
			}
		}
		System.out.println("Closing ...");
	} catch (Exception e) {

	} finally {
		try {
			ClientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```

## Procedura per RMI

1. Copiare codice da Socket
2. Cancellare ServerMain e ServerThread.
3. Nel Client:
   1. Togliere tutto quello che riguarda i socket
   2. Registry registro = LocateRegistry.getRegistry(1099);
   3. ServerInterface serverInt;
   4. serverInt = (ServerInterface) registro.lookup(“nome”)
   5. Creare variabile oggettoCondiviso.
4. Creare ServerInterface
   1. Prendere tutti i metodi della classe condivisa
   2. Togliere tutti i synchronized
   3. Aggiungere a tutti I metodi: throws RemoteException
   4. IMPORTANTE: tutti i metodi devono essere public
5. Creare ServerImpl
   1. extends UnicastRemoteObject implements ServerInterface
      1. public ServerImpl() throws RemoteException {
         super();
         oggettoCondiviso = new classeCondivisa();
         }
6. Si copiano tutti I metodi che si trovano dentro ServerInterface e si fa il return nel caso fossero
   diversi da void.
7. Nel main:

```java
public statuc void main (String[] args) throws RemoteException {
    Registry registo = LocateRegistry.createRegistry(1099);
    ServerImpl serverImpl = new ServerImpl();
    registro.rebind("ServerPartita", serverImpl);
}
```
