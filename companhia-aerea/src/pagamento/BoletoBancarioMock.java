package pagamento;

import javax.swing.JOptionPane;
import java.util.Random;
import principal.Sistema;

public class BoletoBancarioMock implements MetodoPagamento {
 
	String cod = "";
	
	public BoletoBancarioMock() {
		
		for(int i = 0; i<48; i++){
			cod += Integer.toString(new Random().nextInt(9));	
		}
	}
	
	@Override
	public void cobra(double valor) {
		// TODO Auto-generated method stub
		Sistema.mostraAviso("Seu n� do c�digo de barras �: " + cod, JOptionPane.PLAIN_MESSAGE);

	}

	@Override
	public void reembolsa(double valor) {
		// TODO Auto-generated method stub
		
	}

}
