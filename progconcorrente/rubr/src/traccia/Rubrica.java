package traccia;

import java.util.Hashtable;

public class Rubrica implements RubricaInterface{
	Hashtable<String, String> laRubrica;
	public Rubrica(){
		laRubrica = new Hashtable<String, String>();
	}
	public void aggiungiNumero(String nome, String num) {
		if (!laRubrica.containsKey(nome)) {
			laRubrica.put(nome, num);
			System.out.println("traccia.Rubrica: aggiunto " + nome + "  " + num);
		} else {
			System.out.println("traccia.Rubrica: NON aggiungo " + nome + "gia` presente.");
		}
	}
	public void eliminaNumero(String nome) {
		if (laRubrica.containsKey(nome)) {
			laRubrica.remove(nome);
			System.out.println("traccia.Rubrica: rimosso " + nome);
		} else {
			System.out.println("traccia.Rubrica: NON rimosso " + nome + "gia` assente.");
		}
	}
	public boolean inRubrica(String nome) {
		return laRubrica.containsKey(nome);
	}
	public String trova(String nome){
		return laRubrica.get(nome);
	}
	
}
