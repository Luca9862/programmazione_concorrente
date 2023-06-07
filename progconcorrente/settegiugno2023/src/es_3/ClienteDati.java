package es_3;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClienteDati {
	
	int id;
	
	public ClienteDati(int i) {
		this.id = i;
	}
	
	public void exec() throws RemoteException, NotBoundException {

        Registry reg = LocateRegistry.getRegistry(1099);
        DatiInterface server = (DatiInterface) reg.lookup("server");
        		
        server.aggiungiDato("potipu", "info potipu");
        server.aggiungiDato("patagarru", "info patagarru");
        server.aggiungiDato("svicolone", "info svicolone");
        String chiaveDaCercare="svicolone";
        if(server.esisteDato(chiaveDaCercare)) {
            String num = server.trovaDato(chiaveDaCercare);
            System.out.println("Info di "+chiaveDaCercare+" e` "+ num);
        } else {
            System.out.println(chiaveDaCercare+" non trovato ");
        }
	}

    public static void main(String[] args) throws RemoteException, NotBoundException {
        for(int i = 0; i<4; i++) {
        	new ClienteDati(i).exec();
        }
    }
}
