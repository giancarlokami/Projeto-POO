package exceptions;

import aviao.Aviao;
import aviao.Voo;

public class HorarioIndisponivelException extends Exception{

	private static final long serialVersionUID = 1L;
	private Voo voo;
	private Aviao aviao;
	
	public HorarioIndisponivelException(Aviao aviao, Voo voo) {
		super("O horario " + voo.getHora() + " do dia " + voo.getData() + " não esta disponivel para o aviao solicitado");
		this.aviao = aviao;
		this.voo = voo;
	}
	
	public Voo getVoo() {
		return voo;
	}
	
	public Aviao getAviao() {
		return aviao;
	}
}
