package tracciaSocket;

public class DatoScommessa {
	private int numero; // numero su cui si scommette
	private int sommaScommessa; // somma scommessa
	DatoScommessa(int n, int a){
		numero=n;
		sommaScommessa=a;
	}
	public int getAmmontare() {
		return sommaScommessa;
	}
	public void setAmmontare(int ammontare) {
		this.sommaScommessa = ammontare;
	}
	public int getNumero() {
		return numero;
	}
	public String toString() {
		return "Scommessa sul "+numero+" di "+sommaScommessa+" euro";
	}
}
