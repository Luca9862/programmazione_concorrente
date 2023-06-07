package es_1;

public class Main {
	public static void main(String[] args) {
        Dati dati = new Dati();
        for(int i = 0; i<4; i++) {
        	new ClienteDati(dati);
        }
    }
}
