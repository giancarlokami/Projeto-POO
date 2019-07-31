package usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import aviao.Aviao;
import aviao.Poltrona;
import aviao.Voo;

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
			if(escolha.contentEquals("S")) {
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
							+ "+--Horário: %tH:%<tM\n"
							+ "\n"
							, voos.indexOf(v)
							, v.getOrigem()
							, v.getDestino()
							, v.getData()
							, v.getHora());
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
		msg += String.format("\nFim do Relatorio do Voo %02d\n", voos.indexOf(v));
		JOptionPane.showMessageDialog(null, msg, String.format("Relatorio do Voo %02d", voos.indexOf(v)), JOptionPane.PLAIN_MESSAGE);
	}

	public void criaVoo(ArrayList<Aviao> avioes) {
		Voo voo = new Voo(LocalDate.now(), LocalTime.now(), "Sao Paulo", "Rio de Janeiro", 100, 1000);
		voo.setAviao(avioes.get(0));
	}
	
	public void cancelaVoo(ArrayList<Voo> voos) {
		
	}

	@Override
	public String toString() {
		return String.format("Atendente %s", super.getNome()); 
	}
}
