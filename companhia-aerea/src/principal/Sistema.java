package principal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import aviao.Aviao;
import aviao.Voo;
import usuario.Usuario;

public class Sistema {

	private static ArrayList<Aviao> avioes;
	private static ArrayList<Usuario> users;
	private static GerenciadorArquivos<Usuario> gerenciadorUsuarios;
	private static GerenciadorArquivos<Voo> gerenciadorVoos;
	private static GerenciadorLog log;
	private static Scanner sc;
	
	public static void main(String[] args) {
		try {
			log = GerenciadorLog.getInstancia();
			gerenciadorUsuarios = new GerenciadorArquivos<>("users", Usuario.class);
			gerenciadorVoos = new GerenciadorArquivos<>("voos", Voo.class);
		} catch (IOException e) {
			System.out.println("Erro ao carregar um dos arquivos arquivo");
			e.printStackTrace();
			desliga();
		}
		
		log.registrarAcao("Sistema iniciado.");
	}
	
	private static void salvaUsuarios() {
		try {
			gerenciadorUsuarios.salvaDados(users);
			log.registrarAcao("Usuarios salvos.");
		} catch (IOException e) {
			e.printStackTrace();
			log.registrarAcao("Erro ao salvar usuarios.");
		}
	}
	
	private static void carregaUsuarios() {
		
	}
	
	private static void carregaAvioes() {
		
	}
	
	private static void salvaVoos() {
		
	}
	
	private static void menuPrincipal() {
		System.out.println("Bem vindo ao UFABC Airlines. O que você deseja fazer?");
	}
	
	private static void menuCadastraUsuario() {
		
	}
	
	private static void menuLogin() {
		
	}
	
	private static void menuUsuario() {
		
	}
	
	private static void desliga() {
		
	}
}
