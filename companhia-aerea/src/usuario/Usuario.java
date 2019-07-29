package usuario;

import java.io.Serializable;
import java.util.ArrayList;

import aviao.Reserva;
import aviao.Voo;

public abstract class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private ArrayList<Reserva> reservas;
	
	public Usuario(String nome) {

	}

	public String getNome() {
		return nome;
	}

	public ArrayList<Reserva> getReservas() {
		return reservas;
	}
	
	@Override
	public abstract String toString();
	
	public void reservaVoo(Voo voo) {
		
	}
	
	public void cancelaReserva(Reserva reserva) {
		
	}
	
	public void embarca(Voo voo) {
		
	}
	
	public void pagaReserva(Reserva reserva) {
		
	}
}
