package soluzionesocket;

import java.io.Serializable;

public class RichiestaRisposta implements Serializable {
	int id;
	String richiesta;
	String risposta;
	RichiestaRisposta(int n, String s){
		id=n;
		richiesta=s;
		risposta=null;
	}
	public int getId() {
		return id;
	}
	public String getRichiesta() {
		return richiesta;
	}
	public String getRisposta() {
		return risposta;
	}
	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}
	public String toString() {
		return "RichRisp ["+id+", "+richiesta+", "+risposta+"]";
	}
}
