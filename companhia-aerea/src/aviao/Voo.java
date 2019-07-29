package aviao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import exceptions.HorarioIndisponivelException;

public class Voo implements Serializable {

	private static final long serialVersionUID = -8902228118656955748L;
	private LocalDate data;
	private LocalTime hora;
	private String origem;
	private String destino;
	private double precoPrimeiraClasse;
	private double precoClasseEconomica;
	private ArrayList<Reserva> reservas;
	private ArrayList<Poltrona> poltronas;
	private Aviao aviao;
	
	public Voo(LocalDate data, LocalTime hora, String origem, String destino, double precoPrimeiraClasse, double precoClasseEconomica) {
		this.data = data;
		this.hora = hora;
		this.origem = origem;
		this.destino = destino;
		this.precoPrimeiraClasse = precoPrimeiraClasse;
		this.precoClasseEconomica = precoClasseEconomica;
		this.reservas = new ArrayList<>();
		this.poltronas = new ArrayList<>();
	}

	public LocalDate getData() {
		return data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public String getOrigem() {
		return origem;
	}

	public String getDestino() {
		return destino;
	}

	public double getPrecoPrimeiraClasse() {
		return precoPrimeiraClasse;
	}

	public double getPrecoClasseEconomica() {
		return precoClasseEconomica;
	}

	public ArrayList<Reserva> getReservas() {	
		return this.reservas;
	}

	public ArrayList<Poltrona> getPoltronasLivres() {
		return this.poltronas;
	}

	public Aviao getAviao() {
		return aviao;
	}
	
	public void setAviao(Aviao aviao) {
		if(this.aviao != null) {
			this.aviao.removeVoo(this);
		}
		try {
			aviao.addVoo(this);
		} catch (HorarioIndisponivelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.aviao = aviao;
		
	}

	public int getPoltronasLivresPrimeiraClasse() {
		return 0;
	}
	
	public int getPoltronasLivresEconomica() {
		return 0;
	}
	
	public int getQtdPassageirosABordo() {
		return 0;
	}
}
