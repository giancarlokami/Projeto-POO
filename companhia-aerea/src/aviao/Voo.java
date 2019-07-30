package aviao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		inicializaPoltronas();
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
		ArrayList<Poltrona> livres = poltronas;
		for (Poltrona p: poltronas) {
			if(!p.estaOcupada())
				livres.add(p);
		}
		return livres;
		
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
			this.aviao = aviao;
		} catch (HorarioIndisponivelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	public void inicializaPoltronas() {
		String tipo;
		for(int i=1;i<=20;i++) {
			if(i%4==1||i%4==0) tipo = "Janela";
			else tipo = "Corredor";
			Poltrona pol = new Poltrona(i,tipo,true);
			poltronas.add(pol);
		}
		for(int i=21;i<=110;i++) {
			if(i%6==1||i%6==0) tipo = "Janela";
			else if(i%6==2||i%6==5) tipo = "Meio";
			else tipo = "Corredor";
			Poltrona pol = new Poltrona(i,tipo,false);
			poltronas.add(pol);
		}
	}
	public List<Poltrona> getPoltronasLivresPrimeiraClasse() {
		List<Poltrona> polt2 = this.getPoltronasLivres();
		return polt2.stream().filter(p->!p.estaOcupada()&&!p.ehPrimeiraClasse()).collect(Collectors.toList());
	}
	
	public List<Poltrona> getPoltronasLivresEconomica() {
		List<Poltrona> polt2 = this.getPoltronasLivres();
		return polt2.stream().filter(p->!p.estaOcupada()&&p.ehPrimeiraClasse()).collect(Collectors.toList());
	}
	
	public int getQtdPassageirosABordo() {
		int count = 0;
		for(int i=0;i<110;i++) {
			if(!poltronas.get(i).estaOcupada()) count++;
		}
		return count;
	}
}
