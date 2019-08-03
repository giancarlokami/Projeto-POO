package exceptions;

public class AssentoOcupadoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AssentoOcupadoException() {
		super("O assento já está ocupado, por favor escolha outro");
	}
}
