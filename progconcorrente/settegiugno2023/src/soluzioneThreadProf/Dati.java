package soluzioneThreadProf;

import java.util.Hashtable;

public class Dati implements DatiInterface{
	Hashtable<String, String> iDati;
	public Dati(){
		iDati = new Hashtable<String, String>();
	}
	public synchronized void aggiungiDato(String key, String info) {
		System.out.println("Deposito dati: "+Thread.currentThread().getName()+" inserisce info di " + key);
		if (!iDati.containsKey(key)) {
			iDati.put(key, info);
			System.out.println("Deposito dati: aggiunto " + key + "  " + info);
			notifyAll();
		} else {
			System.out.println("Deposito dati: NON aggiungo " + key + " gia` presente.");
		}
	}
	public synchronized void eliminaDato(String key) {
		if (iDati.containsKey(key)) {
			iDati.remove(key);
			System.out.println("Deposito dati: rimosso " + key);
		} else {
			System.out.println("Deposito dati: NON rimosso " + key + " gia` assente.");
		}
	}
	public synchronized boolean esisteDato(String key) {
		return iDati.containsKey(key);
	}
	public synchronized String trovaDato(String key){
		System.out.println("Deposito dati: "+Thread.currentThread().getName()+" cerca info di " + key);
		while(!iDati.containsKey(key)) {
			System.out.println("Deposito dati: "+Thread.currentThread().getName()+" va in attesa");
			try {
				wait();
			} catch (InterruptedException e) { }
		}
		String info=iDati.get(key);
		System.out.println("Deposito dati: trovato "+info+" di " + key);
		return info;
	}
}
