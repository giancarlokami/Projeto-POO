package aviao;

import java.io.Serializable;

import exceptions.AssentoOcupadoException;
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
	
	public void reserva(Usuario user,int idade, boolean ehPrimeiraClasse) throws AssentoOcupadoException {
		if(!this.ocupada){
			this.ocupada = true;
			this.dono = user;
			if(idade>=18) {
				this.ehAdulto = true;
			}else this.ehAdulto = false;
			if(ehPrimeiraClasse) {
				this.primeiraClasse = true;
			}else this.primeiraClasse = false;
			user.reservaVoo(this);
		}else throw new AssentoOcupadoException();
	}
	
	public void cancela() {
		if(this.ocupada==true) {
			this.getUsuario().cancelaReserva(this);
			this.ocupada = false;
			this.dono = null;
			
		}
	}
	
}
