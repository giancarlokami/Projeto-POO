package aviao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import exceptions.HorarioIndisponivelException;

public class Aviao implements Serializable {

	private static final long serialVersionUID = -5348639226293092840L;
	private String modelo;
	private ArrayList<Voo> voos;
	
	public Aviao(String modelo) {
		
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public ArrayList<Voo> getVoos() {
		ArrayList<Voo> copia = new ArrayList<>();
		Collections.copy(copia, voos);
		
		return copia;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	
	public void addVoo(Voo voo) throws HorarioIndisponivelException {
		
	}
	
	public void removeVoo(Voo voo) {
		
	}
}
