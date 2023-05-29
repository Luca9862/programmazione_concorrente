package traccia;

public class RubricaClient {

	public static void main(String[] args) {
		Rubrica miaRubrica = new Rubrica();
		miaRubrica.aggiungiNumero("Zia Pina", "+390212345678");
		miaRubrica.aggiungiNumero("Giorgio", "+390213579");
		miaRubrica.aggiungiNumero("Adalberto", "+390224680");
		String num = miaRubrica.trova("Zia Pina");
		System.out.println("Il numero della zia Pina e` "+ num);
		
	}

}
