package traccia;

public class Elemento {
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
