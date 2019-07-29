package aviao;

import java.io.Serializable;
import java.util.ArrayList;

import exceptions.HorarioIndisponivelException;

public class Aviao implements Serializable {

	private static final long serialVersionUID = -5348639226293092840L;
	private String nome;
	private ArrayList<Voo> voos;
	
	public Aviao(String nome) {
		this.nome = nome;
		this.voos = new ArrayList<>();
	}
	
	public String getNome() {
		return nome;
	}
	
	public ArrayList<Voo> getVoos() {
		return voos;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Aviao) {
			return this.nome.equals(((Aviao) obj).getNome());
		}
		
		return false;
	}
	
	public void addVoo(Voo voo) throws HorarioIndisponivelException {
		this.voos.add(voo);
	}
	
	public void removeVoo(Voo voo) {
		
	}
}
