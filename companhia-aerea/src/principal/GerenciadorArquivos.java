package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivos<T extends Serializable> {

	private String nomeArquivo;
	private Class<T> tipo;

	public GerenciadorArquivos(String nomeArquivo, Class<T> tipo) {
		this.nomeArquivo = nomeArquivo;
		this.tipo = tipo;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public void salvaDados(ArrayList<T> dados) throws IOException {
		if(dados != null) {
			ObjectOutputStream escritorObjs = new ObjectOutputStream(new FileOutputStream(this.nomeArquivo + "-new.bin"));
			escritorObjs.writeObject(dados);
			escritorObjs.close();
			
			sobrescreveAnterior();
		}
	}
	
	public ArrayList<T> carregaDados() throws IOException {
		try {
			ObjectInputStream leitorObjs = new ObjectInputStream(new FileInputStream(this.nomeArquivo + ".bin"));
			ArrayList<T> dados = new ArrayList<>();
			
			List<?> objs = (List<?>) leitorObjs.readObject();
			leitorObjs.close();
			
			for(Object o : objs) {
				dados.add(tipo.cast(o));
			}
			
			return dados;
		} catch (FileNotFoundException e) {
			return new ArrayList<T>();
		} catch (IOException e) {
			throw new IOException(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Estao faltando classes no programa!!");
			System.exit(-1);
			return null;
		}
	}
	
	private void sobrescreveAnterior() {
		File old = new File(this.nomeArquivo + ".bin");
		File curr = new File(this.nomeArquivo + "-new.bin");
		
		if(old.exists()) {
			old.delete();
		} 
		
		curr.renameTo(old);
	}
}
