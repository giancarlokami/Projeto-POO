package aviao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import exceptions.MuitasReservasException;
import pagamento.MetodoPagamento;
import usuario.Usuario;

public class Reserva implements Serializable{

	private static final long serialVersionUID = -5250405250006318152L;
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
		this.poltronas = poltronas;
		calculaValorTotal();
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
	
	public ArrayList<Poltrona> getPoltronas() {
		return poltronas;
	}
	
	public Voo getVoos() {
		return this.voo;
	}
	
	public String getNomeUsuario() {
		return this.usuario.getNome();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public void calculaValorTotal() {
		this.valorTotal = this.voo.getPrecoClasseEconomica() * this.getQtdEconomica() + this.voo.getPrecoPrimeiraClasse() * this.qtdPrimeiraClasse;
	}
	
	public void reservaPoltrona() {
		List<Poltrona> polt2 = voo.getPoltronasLivresPrimeiraClasse();
		for(int i=0;i<qtdPrimeiraClasse;i++) {
			polt2.get(i).reserva();
		}
		polt2.clear();
		polt2 = voo.getPoltronasLivresEconomica();
		for(int i=0;i<qtdEconomica;i++) {
			polt2.get(i).reserva();
		}
		
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
