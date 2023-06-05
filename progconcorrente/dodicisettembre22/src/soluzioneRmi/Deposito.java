package soluzioneRmi;

import java.util.LinkedList;
import java.util.List;

public class Deposito {
	List<RichiestaRisposta> lista;
	Deposito(){
		lista = new LinkedList<RichiestaRisposta>();
	}
	public synchronized RichiestaRisposta LeggiQualunque() throws InterruptedException {
		RichiestaRisposta rr=null;
		while (lista.isEmpty()) {
			wait();
		}
		rr=lista.get(0);
		lista.remove(0);
		return rr;
	}
	private synchronized int rispostaDisponibile(int idx) {
		// se l'elemento avente l'identificatore cercato esiste ne restituisce la posizione nella lista
		// se non c'e` restituisce un numero negativo
		if(lista.isEmpty()) {
			return -999;
		}
		for(int ii=0; ii<lista.size(); ii++) {
			if(idx==lista.get(ii).getId()) {
				return ii;
			}
		}
		return -999;
	}
	public synchronized RichiestaRisposta LeggiIdx(int idx) throws InterruptedException {
		// System.out.println("Deposito leggiRisposta "+idx);
		int trovatoIdx=-999;
		while((trovatoIdx=rispostaDisponibile(idx))<0) {
			wait();
		}
		return lista.get(trovatoIdx);
	}
	public synchronized void Aggiungi(RichiestaRisposta rr) {

		lista.add(rr);
		notifyAll();
	}
}
