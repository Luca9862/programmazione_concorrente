package soluzioneRMIProf;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClienteDati {
	int myId;
	DatiInterface iDati;
	private void exec() {
		try {
			Registry reg = LocateRegistry.getRegistry(1099);
			iDati = (DatiInterface) reg.lookup("DataService");
		} catch (RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int j=0; j<4; j++) {
			ClienteRunnable temp= new ClienteRunnable(j, iDati);
			new Thread(temp).start();
		}
	}
	public static void main(String[] args) {
		new ClienteDati().exec();
	}
}
