package soluzionesocket;

import java.io.Serializable;

public class Numero implements Serializable{
	
	private static final long serialVersionUID = 1;

	String chi;
	String num;
	
	public Numero(String chi, String n) {
		this.chi = chi;
		this.num = n;
	}

	public String getChi() {
		return chi;
	}

	public String getNum() {
		return num;
	}
}
