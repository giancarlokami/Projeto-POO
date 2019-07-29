package pagamento;

public interface MetodoPagamento {

	public void cobra(double valor);
	public void reembolsa(double valor);
}
