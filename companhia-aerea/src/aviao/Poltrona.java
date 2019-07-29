package aviao;

public class Poltrona {

	private int numero;
	private String tipo;
	private boolean primeiraClasse;
	private boolean ocupada;
	
	public Poltrona(int numero, String tipo, boolean primeiraClasse) {
		this.numero = numero;
		this.tipo = tipo;
		this.primeiraClasse = primeiraClasse;
	}

	public int getNumero() {
		return numero;
	}

	public String getTipo() {
		return tipo;
	}

	public boolean ehPrimeiraClasse() {
		return primeiraClasse;
	}

	public boolean estaOcupada() {
		return ocupada;
	}
	
	public void reserva() {
		
	}
	
	public void desocupa() {
		
	}
}
