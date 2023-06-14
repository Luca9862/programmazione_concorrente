package soluzioneSocketProf;

import java.io.*;
import java.net.*;
import java.util.concurrent.ThreadLocalRandom;

public class ClientThread extends Thread {
	ObjectOutputStream oos;
	ObjectInputStream ois;
	Socket mySocket;
	int myId;
	ClientThread(int n){
		myId=n;
		try {
			mySocket = new Socket(InetAddress.getByName(null), DatiServer.PORT);
			oos = new ObjectOutputStream(mySocket.getOutputStream());
			ois = new ObjectInputStream(mySocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setName("client_"+myId);
	}
	private void mySleep(int i1, int i2) {
		try {
			Thread.sleep(ThreadLocalRandom.current().nextInt(i1, i2));
		} catch (InterruptedException e) { }
	}
	public void run() {
		try {
			if((myId%2)==1) {
				oos.writeObject("Aggiungi");
				oos.writeObject("potipu");
				oos.writeObject("info potipu");
				mySleep(0, 2000);
				oos.writeObject("Aggiungi");
				oos.writeObject("patagarru");
				oos.writeObject("info patagarru");
				mySleep(0, 2000);
				oos.writeObject("Aggiungi");
				oos.writeObject("svicolone");
				oos.writeObject("info svicolone");
			} else {
				String chiaveDaCercare="svicolone";
				mySleep(0, 2000);
				System.out.println("Client"+myId+": cerco "+chiaveDaCercare);
				oos.writeObject("Trova");
				oos.writeObject(chiaveDaCercare);
				String info = (String) ois.readObject();
				System.out.println("Client"+myId+": info di "+chiaveDaCercare+" e` "+ info);
			}
			oos.writeObject("FineServizio");
			mySocket.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		for(int j=0; j<4; j++) {
			new ClientThread(j).start();
		}
	}
}
