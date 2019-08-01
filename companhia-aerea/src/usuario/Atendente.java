package usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import aviao.Aviao;
import aviao.Poltrona;
import aviao.Voo;
import exceptions.HorarioIndisponivelException;

public class Atendente extends Usuario {

	private static final long serialVersionUID = 1L;

	public Atendente(String nome) {
		super(nome);
	}
	
	public void geraRelatorio(ArrayList<Voo> voos){
		String msg;
		String escolha;
		int id;
		
		if (voos.size() == 0) {
			msg = "Nao ha voos cadastrados!";
			JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		do {
			msg = "Escolha um voo para gerar um relatorio, ou 'S' para voltar:\n";
			for (Voo v: voos)
				msg += String.format("\n| - %02d. Origem: %s | Destino: %s | Data: %td/%<tm/%<tY:%tH:%<tM"
								, voos.indexOf(v)
								, v.getOrigem()
								, v.getDestino()
								, v.getData()
								, v.getHora());
			escolha = JOptionPane.showInputDialog(null, msg, "Gerar Relatorio de Voo", JOptionPane.PLAIN_MESSAGE);
			if(escolha.toUpperCase().contentEquals("S")) {
				return;
			}
				
			id = Integer.parseInt(escolha);
		} while (id < 0 || id >= voos.size());
		
		Voo v = voos.get(id);
		
		//Incio do relatorio de voo
		msg = String.format(//Informacoes do voo
							  "+Informacoes do Voo %02d: \n"
							+ "+--Origem: %s\n"
							+ "+--Destino: %s\n"
							+ "+--Data: %tA - %<td de %<tB de %<tY\n"
							+ "+--Horario: %tH:%<tM\n"
							+ "+--Aviao: %s\n"
							+ "\n"
							, voos.indexOf(v)
							, v.getOrigem()
							, v.getDestino()
							, v.getData()
							, v.getHora()
							, v.getAviao());
					//Informacoes de passageiros
		msg += String.format("+Informacoes dos Passageiros do Voo %02d:\n", voos.indexOf(v));
		
		for (Poltrona p : v.getPoltronas()) {
			if (p.getUsuario() != null) {
				msg += String.format("+--Poltrona %02d: %s\n", v.getPoltronas().indexOf(p) + 1, p.getUsuario());
			}
		}
			
		msg += String.format( "+--Numero de passageiros a bordo: %d\n"
							+ "+--Poltronas Livres: %d\n"
							+ "+--Poltronas Economicas: %d\n"
							+ "+--Poltronas 1a classe: %d\n"
							+ "+--Numero de passageiros menores: %d\n"
							, v.getQtdPassageirosABordo()
							, v.getPoltronasLivres().size()
							, v.getPoltronasLivresEconomica().size()
							, v.getPoltronasLivresPrimeiraClasse().size()
							, v.getPoltronas().stream().filter(p -> p.getUsuario() != null && p.getUsuario().getIdade() < 18).collect(Collectors.toList()).size());
		msg += String.format( "\n+Informacoes das Passagens do Voo %02d:\n", voos.indexOf(v));
		
		msg += String.format( "+--Preco passagem economica: R$ %.2f\n"
							+ "+--Preco passagem primeira classe: R$ %.2f\n"
							+ "+--Total em passagens da classe economica: R$ %.2f\n"
							+ "+--Total em passagens da primeira classe: R$ %.2f\n"
							+ "+--Total em passagens: R$ %.2f\n"
				, v.getPrecoClasseEconomica()
				, v.getPrecoPrimeiraClasse()
				, v.getValorTotalClasseEconomica()
				, v.getValorTotalPrimeiraClasse()
				, v.getValorTotal());
		msg += String.format("\nFim do Relatorio do Voo %02d\n", voos.indexOf(v));
		JOptionPane.showMessageDialog(null, msg, String.format("Relatorio do Voo %02d", voos.indexOf(v)), JOptionPane.PLAIN_MESSAGE);
	}

	public void criaVoo(ArrayList<Aviao> avioes) throws HorarioIndisponivelException {
		String msg, in;
		LocalDate data;
		LocalTime hora;
		String origem, destino;
		Aviao aviao;
		ArrayList<Aviao> disponiveis;
		double precoPrimeiraClasse, precoClasseEconomica;
		//Escolha de data
		do {
			msg = "Digite a data do voo no formato 'dd/mm/yyyy', ou 'S' para voltar:";
			in = JOptionPane.showInputDialog(null, msg, "Criar Voo", JOptionPane.PLAIN_MESSAGE);
			if (in.toUpperCase().contentEquals("S")) {
				return;
			}
			if (!checaData(in)) {
				msg = "Formato inválido!";
				JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
			}
		} while (!checaData(in));
		data = LocalDate.parse(in, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		//Escolha de hora 
		do {
			msg = "Digite a hora do voo no formato 'hh:mm', ou 'S' para voltar:";
			in = JOptionPane.showInputDialog(null, msg, "Criar Voo", JOptionPane.PLAIN_MESSAGE);
			if (in.toUpperCase().contentEquals("S")) {
				return;
			}
			if (!checaHora(in)) {
				msg = "Formato inválido!";
				JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
			}
		} while (!checaHora(in));
		hora = LocalTime.parse(in, DateTimeFormatter.ofPattern("HH:mm"));
		
		//Escolha de origem
		msg = "Digite a origem do voo, use '_' ou '-' no lugar de espacos, ou 'S' para voltar:";
		in = JOptionPane.showInputDialog(null, msg, "Criar Voo", JOptionPane.PLAIN_MESSAGE).replaceAll("[_-]", " ");;
		if (in.toUpperCase().contentEquals("S")) {
			return;
		}
		origem = in;
		
		//Escolha de destino
		msg = "Digite o destino do voo, use '_' ou '-' no lugar de espacos, ou 'S' para voltar:";
		in = JOptionPane.showInputDialog(null, msg, "Criar Voo", JOptionPane.PLAIN_MESSAGE).replaceAll("[_-]", " ");;
		if (in.toUpperCase().contentEquals("S")) {
			return;
		}
		destino = in;
		
		//Escolha preco primeiro classe
		msg = "Digite o preco da primeira classe, ou 'S' para sair:";
		in = JOptionPane.showInputDialog(null, msg, "Criar Voo", JOptionPane.PLAIN_MESSAGE);
		if (in.toUpperCase().contentEquals("S")) {
			return;
		}
		precoPrimeiraClasse = Double.parseDouble(in);
		
		//Escolha preco classe economica
		msg = "Digite o preco da classe economica, ou 'S' para sair:";
		in = JOptionPane.showInputDialog(null, msg, "Criar Voo", JOptionPane.PLAIN_MESSAGE);
		if (in.toUpperCase().contentEquals("S")) {
			return;
		}
		precoClasseEconomica = Double.parseDouble(in);
		
		//Escolha do aviao
		Voo voo = new Voo(data, hora, origem, destino, precoPrimeiraClasse, precoClasseEconomica);
		disponiveis = getAvioesDisponiveis(avioes, voo);
		if(disponiveis.size() == 0) {
			msg = "Não há aviões disponíveis para realizar o voo!";
			JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
		} else {
			do {
				msg = "Escolha um avião para realizar o voo, ou 'S' para sair:\n";
				for (Aviao d: disponiveis) {
					msg += String.format("\n|- %d. - %s", disponiveis.indexOf(d), d);
				}
				in = JOptionPane.showInputDialog(null, msg, "Criar Voo", JOptionPane.PLAIN_MESSAGE);
				if (in.toUpperCase().contentEquals("S")) {
					return;
				}
				if (Integer.parseInt(in) < 0 || Integer.parseInt(in) >= disponiveis.size()) {
					msg = "Selecione um aviao dentre as opcoes possiveis!";
					JOptionPane.showMessageDialog(null, msg, "Erro", JOptionPane.ERROR_MESSAGE);
				}
			} while (Integer.parseInt(in) < 0 || Integer.parseInt(in) >= disponiveis.size());
		}
		aviao = disponiveis.get(Integer.parseInt(in));
		voo.setAviao(aviao);
		msg = "Voo criado com sucesso!";
		JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.PLAIN_MESSAGE);
	}
	
	public void cancelaVoo(ArrayList<Voo> voos) {
		
	}

	@Override
	public String toString() {
		return String.format("Atendente %s", super.getNome()); 
	}
	
	private boolean checaData(String strData) {
		try {
			LocalDate.parse(strData, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean checaHora(String strHora) {
		try {
			LocalTime.parse(strHora, DateTimeFormatter.ofPattern("HH:mm"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private ArrayList<Aviao> getAvioesDisponiveis(ArrayList<Aviao> avioes, Voo voo) {
		ArrayList<Aviao> disponiveis = (ArrayList<Aviao>) avioes.stream().filter(a -> a.verificaDisponibilidade(voo)).collect(Collectors.toList());
		return disponiveis;
	}
}
