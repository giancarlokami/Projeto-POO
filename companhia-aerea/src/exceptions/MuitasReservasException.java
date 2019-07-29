package exceptions;

import aviao.Voo;

public class MuitasReservasException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private int poltronasPrimeiraClasse;
	private int poltronasEconomica;
	
	public MuitasReservasException(Voo voo) {
		super("O Voo não possui poltronas suficientes para essa reserva.");
		
		this.poltronasPrimeiraClasse = voo.getPoltronasLivresPrimeiraClasse();
		this.poltronasEconomica = voo.getPoltronasLivresEconomica();
	}

	public int getPoltronasPrimeiraClasse() {
		return poltronasPrimeiraClasse;
	}

	public int getPoltronasEconomica() {
		return poltronasEconomica;
	}
}
