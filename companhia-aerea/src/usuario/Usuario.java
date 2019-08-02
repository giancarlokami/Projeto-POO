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
	
	public void reservaVoo(Poltrona poltrona) {
		this.reservadas.add(poltrona);
	}
	
	public void cancelaReserva(Poltrona poltrona) {
		this.reservadas.remove(poltrona);
	}
	
	public void imprimeReservas() {
		this.reservadas.stream().forEach(c->System.out.println(c));
	}
	
	public void embarca(Voo voo) {
		
	}
	
	public int getIdade() {
		return idade;
	}

	private void setIdade(int idade) {
		this.idade = idade;
	}
}
