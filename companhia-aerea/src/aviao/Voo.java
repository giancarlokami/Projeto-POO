package aviao;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import usuario.Passageiro;

public class Voo implements Serializable {

	private static final long serialVersionUID = -8902228118656955748L;
	private LocalDate data;
	private LocalTime hora;
	private String origem;
	private String destino;
	private double precoPrimeiraClasse;
	private double precoClasseEconomica;
	private ArrayList<Poltrona> poltronas;
	private Aviao aviao;
	
	public Voo(LocalDate data, LocalTime hora, String origem, String destino, double precoPrimeiraClasse, double precoClasseEconomica) {
		this.data = data;
		this.hora = hora;
		this.origem = origem;
		this.destino = destino;
		this.precoPrimeiraClasse = precoPrimeiraClasse;
		this.precoClasseEconomica = precoClasseEconomica;
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

	public ArrayList<Poltrona> getPoltronasLivres() {
		return (ArrayList<Poltrona>) poltronas.stream().filter(p -> !p.estaOcupada()).collect(Collectors.toList());
	}

	public Aviao getAviao() {
		return aviao;
	}
	
	public void setAviao(Aviao aviao) {
		if(this.aviao != null) {
			this.aviao.removeVoo(this);
		}
		aviao.addVoo(this);
		this.aviao = aviao;
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
	
	public ArrayList<Poltrona> getPoltronasLivresPrimeiraClasse() {
		return (ArrayList<Poltrona>) getPoltronas().stream().filter(p -> !p.estaOcupada() && p.ehPrimeiraClasse()).collect(Collectors.toList());
	}
	
	public ArrayList<Poltrona> getPoltronasLivresEconomica() {
		return (ArrayList<Poltrona>) getPoltronas().stream().filter(p -> !p.estaOcupada() && !p.ehPrimeiraClasse()).collect(Collectors.toList());
	}
	
	public int getQtdPassageirosABordo() {
		int count = 0;
		for(int i=0;i<110;i++) {
			if(poltronas.get(i).estaOcupada()) count++;
		}
		return count;
	}

	public ArrayList<Poltrona> getPoltronas() {
		return poltronas;
	}
	
	public int getQtdPoltronasPrimeiraClasseOcupadas() {
		return poltronas.stream().filter(p -> p.ehPrimeiraClasse() && p.estaOcupada()).collect(Collectors.toList()).size();
	}
	
	public int getQtdPoltronasClasseEconomicaOcupadas() {
		return poltronas.stream().filter(p -> !p.ehPrimeiraClasse() && p.estaOcupada()).collect(Collectors.toList()).size();
	}
	
	public int getQtdPoltronasLivres() {
		return poltronas.stream().filter(p -> !p.estaOcupada()).collect(Collectors.toList()).size();
	}
	
	public double getValorTotalClasseEconomica() {
		int qtdEconAdulto = getPoltronas().stream().filter(p -> !p.ehPrimeiraClasse() && p.estaOcupada()&&p.ehAdulto()).collect(Collectors.toList()).size();
		int qtdEconCrianca = getPoltronas().stream().filter(p -> !p.ehPrimeiraClasse() && p.estaOcupada()&&!p.ehAdulto()).collect(Collectors.toList()).size();
		return (qtdEconAdulto * getPrecoClasseEconomica())+(qtdEconCrianca*getPrecoClasseEconomica()*0.5);
	}
	
	public double getValorTotalPrimeiraClasse() {
		int qtdPrimAdulto = getPoltronas().stream().filter(p -> p.ehPrimeiraClasse() && p.estaOcupada()&&p.ehAdulto()).collect(Collectors.toList()).size();
		int qtdPrimCrianca = getPoltronas().stream().filter(p -> p.ehPrimeiraClasse() && p.estaOcupada()&&!p.ehAdulto()).collect(Collectors.toList()).size();
		return (qtdPrimAdulto * getPrecoPrimeiraClasse())+(qtdPrimCrianca * getPrecoPrimeiraClasse() * 0.5);
	}
	
	public double getValorTotal() {
		return getValorTotalClasseEconomica() + getValorTotalPrimeiraClasse();
	}
	
	@Override
	public String toString() {
		return String.format("Origem: %s | Destino: %s | Data: %s:%s | Aviao: %s", getOrigem(), getDestino(), getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), getHora().format(DateTimeFormatter.ofPattern("HH:mm")), getAviao());
	}

	public int getQtdPassageirosMenores() {
		return poltronas.stream().filter(p -> p.getUsuario() != null && p.getUsuario() instanceof Passageiro && p.getUsuario().getIdade() < 18).collect(Collectors.toList()).size();
	}

	public double getTotalDeVendas() {
		return precoClasseEconomica * getQtdPoltronasClasseEconomicaOcupadas() +
				precoPrimeiraClasse * getQtdPoltronasPrimeiraClasseOcupadas();
	}
}
