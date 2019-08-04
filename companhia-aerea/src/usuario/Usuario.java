package usuario;

import java.io.Serializable;
import java.util.ArrayList;

import aviao.Poltrona;
import aviao.Voo;

public abstract class Usuario implements Serializable {
	
	private static final long serialVersionUID = -343373518797799409L;
	private String nome;
	private int idade;
	private ArrayList<Poltrona> reservadas;

	public Usuario(String nome, int idade) {
		setNome(nome);
		setIdade(idade);
		this.reservadas = new ArrayList<Poltrona>();
	}
	
	public Usuario(String nome) {
		setNome(nome);
		setIdade(18);
		this.reservadas = new ArrayList<Poltrona>();
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Usuario) {
			return this.nome.equals(((Usuario) obj).getNome());
		}
		
		return false;
	}
	
	public void consultaResrvas() {
		
	}
	
	public void pagaReserva() {
		
	}
	
	public void cancelaReserva(Poltrona poltrona) {
		this.reservadas.remove(poltrona);
	}
	
	
	public void alteraNome() {
		
	}
	
	public void reservaPoltrona(Poltrona poltrona) {
		this.reservadas.add(poltrona);
		poltrona.reserva(this);
	}
	
	public void embarca(Voo voo) {
		
	}
	
	public int getIdade() {
		return idade;
	}

	private void setIdade(int idade) {
		this.idade = idade;
	}

	public ArrayList<Poltrona> getReservas() {
		return reservadas;
	}
}
