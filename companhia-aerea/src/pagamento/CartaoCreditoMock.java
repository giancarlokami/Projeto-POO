
package pagamento;

import javax.swing.JOptionPane;

import principal.Sistema;

public class CartaoCreditoMock implements MetodoPagamento {
	
	public static String nomeCartao;
	public static String numCartao;
	public static int cvv;
	public static String dataValidade;
	
	public CartaoCreditoMock(String numCartao, String nomeCartao, String dataValidade, int cvv) {
		
		this.numCartao = numCartao;
		this.nomeCartao = nomeCartao;
		this.dataValidade = dataValidade;
		this.cvv = cvv;
	}
	
	@Override
	public void cobra(double valor) {
		Sistema.mostraAviso(String.format("R$ %.02f pagos com cartao de credito", valor), JOptionPane.PLAIN_MESSAGE);
	}

	@Override
	public void reembolsa(double valor) {
		// TODO Auto-generated method stub
		
	}

}

