package usuario;

import java.util.ArrayList;

import aviao.Reserva;
import aviao.Voo;

public abstract class Usuario {
	
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
