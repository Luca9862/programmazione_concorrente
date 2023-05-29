package traccia;

public interface RubricaInterface {
	public void aggiungiNumero(String nome, String num);
	public void eliminaNumero(String nome);
	public boolean inRubrica(String nome);
	public String trova(String nome);
}
