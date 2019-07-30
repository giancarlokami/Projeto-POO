package usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import aviao.Aviao;
import aviao.Voo;

public class Atendente extends Usuario {

	private static final long serialVersionUID = 1L;

	public Atendente(String nome) {
		super(nome);
		// TODO Auto-generated constructor stub
	}
	
	public void geraRelatorio(ArrayList<Voo> voos){
		Scanner sc = new Scanner(System.in);
		int id;
		if (voos.size() == 0) {
			System.out.println("Não há voos cadastrados!");
			return;
		}
		do {
			System.out.println("Escolha um voo para gerar um relatório:");
			for (Voo v: voos)
				System.out.printf("| - %02d. Origem: %s | Destino: %s | Data: %td/%<tm/%<tY:%tH:%<tM\n"
								, voos.indexOf(v)
								, v.getOrigem()
								, v.getDestino()
								, v.getData()
								, v.getHora());
			id = Integer.parseInt(sc.nextLine());
		} while (id < 0 || id >= voos.size());
		Voo v = voos.get(id);
			System.out.printf("------ Relatório do Voo %02d ------\n"
							+ "Origem: %s\n"
							+ "Destino: %s\n"
							+ "Data: %tA - %<td de %<tB de %<tY\n"
							+ "Horário: %tH:%<tM\n"
							+ "Passageiros à bordo: %d\n"
							+ "Poltronas Livres: %d\n"
							+ "Poltronas Econômicas: %d\n"
							+ "Poltronas 1ª classe: %d\n"
							+ "-------------------------------\n"
							, voos.indexOf(v)
							, v.getOrigem()
							, v.getDestino()
							, v.getData()
							, v.getHora()
							, v.getQtdPassageirosABordo()
							, v.getPoltronasLivres().size()
							, v.getPoltronasLivresEconomica()
							, v.getPoltronasLivresPrimeiraClasse());
	}

	public void criaVoo(ArrayList<Aviao> avioes) {
		Voo voo = new Voo(LocalDate.now(), LocalTime.now(), "Sao Paulo", "Rio de Janeiro", 100, 1000);
		voo.setAviao(avioes.get(0));
	}
	
	public void cancelaVoo(ArrayList<Voo> voos) {
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
