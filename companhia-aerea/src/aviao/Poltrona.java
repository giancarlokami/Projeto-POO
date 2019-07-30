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
	private String idade;
	

	public Poltrona(int numero, String tipo, boolean primeiraClasse) {
		this.numero = numero;
		this.tipo = tipo;
		this.primeiraClasse = primeiraClasse;
	}

	public Usuario getUsuario() {
		return dono;
	}
	public String getIdade() {
		return idade;
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
	
	public void reserva(Usuario user,String idade) {
		this.ocupada = true;
		this.dono = user;
		this.idade = idade;
		
	}
	
	public void cancela() {
		if(this.ocupada==true) {
			this.ocupada = false;
			this.dono = null;
			this.idade = null;
		}
	}
	
}
