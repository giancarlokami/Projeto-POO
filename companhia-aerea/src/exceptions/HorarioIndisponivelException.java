package exceptions;

import aviao.Aviao;
import aviao.Voo;

public class HorarioIndisponivelException extends Exception{

	private Voo voo;
	private Aviao aviao;
	
	public HorarioIndisponivelException(Aviao aviao, Voo voo) {
		super("O horario " + voo.getHora() + " do dia " + voo.getData() + " n�o esta disponivel para o aviao solicitado");
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
