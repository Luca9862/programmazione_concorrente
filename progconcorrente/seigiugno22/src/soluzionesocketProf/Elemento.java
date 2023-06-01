package soluzionesocketProf;

import java.io.Serializable;

public class Elemento implements Serializable{
	private static final long serialVersionUID = 1L;
	String descrizione;
	int quantita;
	Elemento(String s, int q){
		descrizione=s;
		quantita=q;
	}
	public String toString() {
		return "element "+descrizione+" ("+quantita+")";
	}
}
