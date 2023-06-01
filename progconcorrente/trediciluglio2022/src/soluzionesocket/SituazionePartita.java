package soluzionesocket;

import java.io.Serializable;

public class SituazionePartita implements Serializable {
	private static final long serialVersionUID = 1L;
	// irrilevante per il problema dato
	String situazione;
	SituazionePartita(){
		situazione="iniziale";
	}
	void aggiornaSituazione(String s) {
		situazione=s;
	}
}
