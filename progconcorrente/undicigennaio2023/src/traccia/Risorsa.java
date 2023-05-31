package traccia;

public class Risorsa {
	String tipo;
	Risorsa(String t){
		tipo=t;
	}
	public void changeTypeTo(String t) {
		tipo=t;
	}
	public String toString() {
		return tipo;
	}
}
