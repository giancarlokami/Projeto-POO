package usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import aviao.Aviao;
import aviao.Poltrona;
import aviao.Voo;

public class Atendente extends Usuario {

	private static final long serialVersionUID = 1L;

	public Atendente(String nome) {
		super(nome);
		// TODO Auto-generated constructor stub
	}
	
	public void geraRelatorio(ArrayList<Voo> voos){
		Scanner sc = new Scanner(System.in);
		String escolha;
		int id;
		if (voos.size() == 0) {
			System.out.println("Não há voos cadastrados!");
			return;
		}
		do {
			System.out.println("Escolha um voo para gerar um relatório, ou 'S' para voltar: ");
			for (Voo v: voos)
				System.out.printf("| - %02d. Origem: %s | Destino: %s | Data: %td/%<tm/%<tY:%tH:%<tM\n"
								, voos.indexOf(v)
								, v.getOrigem()
								, v.getDestino()
								, v.getData()
								, v.getHora());
			escolha = sc.nextLine();
			if(escolha.contentEquals("S"))
				return;
			id = Integer.parseInt(escolha);
		} while (id < 0 || id >= voos.size());
		Voo v = voos.get(id);
		//Início do relatório de voo
			System.out.printf("------ Relatório do Voo %02d ------\n"
						//Informações do voo
							+ "-> Informações do Voo%<02d <-"
							+ "Origem: %s\n"
							+ "Destino: %s\n"
							+ "Data: %tA - %<td de %<tB de %<tY\n"
							+ "Horário: %tH:%<tM\n"
							+ "Número de passageiros à bordo: %d\n"
							+ "Poltronas Livres: %d\n"
							+ "Poltronas Econômicas: %d\n"
							+ "Poltronas 1ª classe: %d\n"
							+ "-----------------------------------\n"
							, voos.indexOf(v)
							, v.getOrigem()
							, v.getDestino()
							, v.getData()
							, v.getHora()
							, v.getQtdPassageirosABordo()
							, v.getPoltronasLivres().size()
							, v.getPoltronasLivresEconomica().size()
							, v.getPoltronasLivresPrimeiraClasse().size());
						//Informações de passageiros
			System.out.printf("-> Informações dos Passageiros do Voo %02d <-\n", voos.indexOf(v));
			for (Poltrona p : v.getPoltronas())
				if (p.getUsuario() != null)
					System.out.printf("Poltrona %02d: %s\n", v.getPoltronas().indexOf(p) + 1, p.getUsuario());
				System.out.printf("Número de passageiros menores: %d\n", v.getPoltronas().stream().filter(p -> p.getUsuario() != null && p.getUsuario().getIdade() < 18).collect(Collectors.toList()).size());
			System.out.printf("--- Fim do Relatório do Voo %02d --\n", voos.indexOf(v));
				
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
