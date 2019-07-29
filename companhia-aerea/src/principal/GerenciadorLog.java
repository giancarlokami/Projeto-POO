package principal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GerenciadorLog implements AutoCloseable {
	
	private static GerenciadorLog instancia;
	private SimpleDateFormat formatoData;
	private BufferedWriter br;
	
	private GerenciadorLog() throws IOException {
		br = new BufferedWriter(new FileWriter("log.txt", true));
		formatoData = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	public static GerenciadorLog getInstancia() throws IOException {
		if(instancia == null) {
			instancia = new GerenciadorLog();
		}
		
		return instancia;
	}
	
	public void registrarAcao(String acao) {
		try {
			Date data = new Date();
			br.write("[" + formatoData.format(data) + "] " + acao);
			br.newLine();
		} catch (Exception e) {
			System.out.println("Erro ao registrar acao: " + e);
		}
	}

	@Override
	public void close() throws Exception {
		instancia.br.close();
		instancia = null;
	}
}
