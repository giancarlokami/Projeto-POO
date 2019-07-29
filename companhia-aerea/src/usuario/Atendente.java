package usuario;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import aviao.Aviao;
import aviao.Voo;

public class Atendente extends Usuario {

	private static final long serialVersionUID = 1L;

	public Atendente(String nome) {
		super(nome);
		// TODO Auto-generated constructor stub
	}
	
	public void geraRelatorio(ArrayList<Voo> voos) {
		
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
