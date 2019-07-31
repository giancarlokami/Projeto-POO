package usuario;

public class Passageiro extends Usuario {

	private static final long serialVersionUID = 1L;

	public Passageiro(String nome, int idade) {
		super(nome, idade);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return String.format("Passageiro %s | Idade: %d", super.getNome(), super.getIdade()); 
	}

}
