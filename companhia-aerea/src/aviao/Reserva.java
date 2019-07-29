package aviao;

import java.util.ArrayList;
import java.util.Collections;

import exceptions.MuitasReservasException;
import pagamento.MetodoPagamento;
import usuario.Usuario;

public class Reserva {

	private double valorTotal;
	private int qtdCriancas;
	private int qtdAdultos;
	private int qtdPrimeiraClasse;
	private int qtdEconomica;
	private boolean paga;
	private boolean embarcou;
	private MetodoPagamento pagamento;
	private Usuario usuario;
	private Voo voo;
	private ArrayList<Poltrona> poltronas;
	
	public Reserva(int qtdCriancas, int qtdAdultos, int qtdPrimeiraClasse, int qtdEconomica, Usuario usuario, Voo voo, ArrayList<Poltrona> poltronas) {
		this.qtdCriancas = qtdCriancas;
		this.qtdAdultos = qtdAdultos;
		this.qtdPrimeiraClasse = qtdPrimeiraClasse;
		this.qtdEconomica = qtdEconomica;
		this.usuario = usuario;
		this.voo = voo;
		this.poltronas = new ArrayList<>();
		
		Collections.copy(this.poltronas, poltronas);
	}

	public MetodoPagamento getPagamento() {
		return pagamento;
	}
	
	public void setPagamento(MetodoPagamento pagamento) {
		this.pagamento = pagamento;
	}
	
	public double getValorTotal() {
		return valorTotal;
	}
	
	public int getQtdCriancas() {
		return qtdCriancas;
	}
	
	public int getQtdAdultos() {
		return qtdAdultos;
	}
	
	public int getQtdPrimeiraClasse() {
		return qtdPrimeiraClasse;
	}
	
	public int getQtdEconomica() {
		return qtdEconomica;
	}
	
	public boolean foiPaga() {
		return paga;
	}
	
	public boolean embarcou() {
		return embarcou;
	}
	
	public void addAdultos(int qtd) throws MuitasReservasException {
		
	}
	
	public void addCriancas(int qtd) throws MuitasReservasException {
		
	}
	
	public void cancela() {
		
	}
	
	public void paga(MetodoPagamento pagamento) {
		
	}
	
	public void embarca() {
		
	}
}
