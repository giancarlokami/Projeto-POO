package aviao;

import java.io.Serializable;

import usuario.Usuario;

public class Poltrona implements Serializable {

	private static final long serialVersionUID = -8110758105945324153L;
	private int numero;
	private String tipo;
	private boolean primeiraClasse;
	private boolean ocupada;
	private Usuario dono;
	private boolean ehAdulto;
	

	public Poltrona(int numero, String tipo, boolean primeiraClasse) {
		this.numero = numero;
		this.tipo = tipo;
		this.primeiraClasse = primeiraClasse;
	}
	public boolean ehAdulto() {
		return this.ehAdulto;
	}
	public Usuario getUsuario() {
		return dono;
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
	
	public void reserva(Usuario user) {
		this.dono = user;
		this.ocupada = true;
	}
	
	public void cancela() {
		this.dono = null;
		this.ocupada = false;
	}
	
}
