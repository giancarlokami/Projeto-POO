package aviao;

import java.util.ArrayList;
import java.util.Collections;

import exceptions.HorarioIndisponivelException;

public class Aviao {

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
