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
		return nome;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Aviao) {
			return this.nome.equals(((Aviao) obj).getNome());
		}
		
		return false;
	}
	
	public void addVoo(Voo voo) throws HorarioIndisponivelException {
		if (voos.size() > 0) {
			for(Voo vooat : voos) {
				if(voo.getData() == vooat.getData() && voo.getHora() == vooat.getHora()){
					throw new HorarioIndisponivelException(this, voo);
				}
			}
		}
		this.voos.add(voo);
	}
	
	public boolean verificaDisponibilidade(Voo voo) {
		if (voos.size() == 0) {
			return true;
		}
		for (Voo v : voos) {
			if (v.getData() == voo.getData() && v.getHora() == voo.getHora()) {
				return false;
			}
		}
		return true;
	}
	
	public void removeVoo(Voo voo) {
		for(Voo vooat : voos) {
			if(vooat.equals(voo)) {
				vooat = null;
			}
		}
	}
}
