package soluzioneSocketprof;

import java.io.Serializable;

public class DatoScommessa implements Serializable {
	private static final long serialVersionUID = 1L;
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
