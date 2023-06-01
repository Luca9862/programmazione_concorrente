package soluzioneprof;

import java.util.LinkedList;
import java.util.List;

public class Deposito {
	List<RichiestaRisposta> lista;
	Deposito(){
		lista = new LinkedList<RichiestaRisposta>();
	}
	public synchronized void stampaTutto() {
		System.out.println("Deposito: ");
		for(RichiestaRisposta rr: lista) {
			System.out.println(rr);
		}
	}
	public synchronized RichiestaRisposta LeggiQualunque() {
		RichiestaRisposta rr=null;
		while(lista.isEmpty()) {
			try { wait(); } catch (InterruptedException e) { }
		}
		rr=lista.get(0);
		lista.remove(0);
		return rr;
	}
	private int rispostaDisponibile(int idx) {
		int foundIdx=-999;
		if(lista.isEmpty()) {
			return foundIdx;
		}
		for(int ii=0; ii<lista.size(); ii++) {
			if(idx==lista.get(ii).getId()) {
				return ii;
			}
		}
		return foundIdx;
	}
	public synchronized RichiestaRisposta LeggiIdx(int idx) {
		// System.out.println("Deposito leggiRisposta "+idx);
		RichiestaRisposta trr=null;
		int trovatoIdx=-999;
		while((trovatoIdx=rispostaDisponibile(idx))<0) {
			try { wait(); } catch (InterruptedException ignored) { }
		}
		// stampaTutto();
		trr=lista.get(trovatoIdx);
		return trr;
	}
	public synchronized void Aggiungi(RichiestaRisposta rr) {
		lista.add(rr);
		notifyAll();
	}
}