package usuario;

import java.io.Serializable;
import java.util.ArrayList;

import aviao.Reserva;
import aviao.Voo;

public abstract class Usuario implements Serializable {
	
	private static final long serialVersionUID = -343373518797799409L;
	private String nome;
	private ArrayList<Reserva> reservas;
	
	public Usuario(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	
	@Override
	public abstract String toString();
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Usuario) {
			return this.nome.equals(((Usuario) obj).getNome());
		}
		
		return false;
	}
	
	public void reservaVoo(Voo voo) {
		
	}
	
	public void cancelaReserva(Reserva reserva) {
		
	}
	
	public void embarca(Voo voo) {
		
	}
	
	public void pagaReserva(Reserva reserva) {
		
	}
}
