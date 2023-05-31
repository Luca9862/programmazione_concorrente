package traccia;

public class SituazionePartita {
	// irrilevante per il problema dato
	String situazione;
	SituazionePartita(){
		situazione="iniziale";
	}
	void aggiornaSituazione(String s) {
		situazione=s;
	}
}
