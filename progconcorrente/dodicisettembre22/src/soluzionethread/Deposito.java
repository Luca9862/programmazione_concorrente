package soluzionethread;

import java.util.LinkedList;
import java.util.List;

public class Deposito {
	List<RichiestaRisposta> lista;
	Deposito(){
		lista = new LinkedList<RichiestaRisposta>();
	}
	public RichiestaRisposta LeggiQualunque() {
		RichiestaRisposta rr=null;
		if (lista.isEmpty()) {
			return null;
		}
		rr=lista.get(0);
		lista.remove(0);
		return rr;
	}
	private int rispostaDisponibile(int idx) {
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
	public synchronized RichiestaRisposta LeggiIdx(int idx) {
		// System.out.println("Deposito leggiRisposta "+idx);
		int trovatoIdx=-999;
		if((trovatoIdx=rispostaDisponibile(idx))<0) {
			return null;
		}
		return lista.get(trovatoIdx);
	}
	public synchronized void Aggiungi(RichiestaRisposta rr) {
		lista.add(rr);
	}
}
