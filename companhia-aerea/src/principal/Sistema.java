package principal;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import aviao.Aviao;
import aviao.Voo;
import usuario.Atendente;
import usuario.Passageiro;
import usuario.Usuario;
import view.MenuPrincipal.ViewMenuPrincipal;

public class Sistema {
	
	public static final int qtdAvioes = 4;
	private static ArrayList<Aviao> avioes;
	private static ArrayList<Usuario> users;
	private static GerenciadorArquivos<Usuario> gerenciadorUsuarios;
	private static GerenciadorArquivos<Voo> gerenciadorVoos;
	private static GerenciadorLog log;
	private static Usuario usuarioAtual;
	
	public static void main(String[] args) {
		try {
			log = GerenciadorLog.getInstancia();
			gerenciadorUsuarios = new GerenciadorArquivos<>("users", Usuario.class);
			gerenciadorVoos = new GerenciadorArquivos<>("voos", Voo.class);
		} catch (IOException e) {
			System.out.println("Erro ao carregar um dos arquivos.");
			e.printStackTrace();
			
			log.registrarAcao("Erro ao carregar um dos arquivos, causa:");
			log.registrarAcao(e.getStackTrace().toString());
			
			desliga();
		} catch (Exception e) {
			System.out.println("Erro inesperado");
			e.printStackTrace();
			
			log.registrarAcao("Erro inesperado:");
			log.registrarAcao(e.getStackTrace().toString());
			
			desliga();
		}
		
		log.registrarAcao("Sistema iniciado.");
		carregaUsuarios();
		carregaAvioes();
		new ViewMenuPrincipal().setVisible(true);
		//menuPrincipal();
	}
	
	private static void salvaUsuarios() throws IOException {
		try {
			gerenciadorUsuarios.salvaDados(users);
			log.registrarAcao("Usuarios salvos com sucesso.");
		} catch (IOException e) {
			System.out.println("Erro ao salvar usuarios.");
			e.printStackTrace();
			
			log.registrarAcao("Erro ao salvar usuarios, causa:");
			log.registrarAcao(e.getStackTrace().toString());
			
			throw new IOException(e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado");
			e.printStackTrace();
			
			log.registrarAcao("Erro inesperado:");
			log.registrarAcao(e.getStackTrace().toString());
			
			throw new RuntimeException();
		}
	}
	
	private static void carregaUsuarios() {
		if(users == null) {
			try {
				users = (ArrayList<Usuario>) gerenciadorUsuarios.carregaDados();
				log.registrarAcao("Usuarios carregados com sucesso.");
			} catch (IOException e) {
				users = null;
				
				System.out.println("Erro ao ler usuarios");
				e.printStackTrace();
				
				log.registrarAcao("Erro ao ler usuarios, causa:");
				log.registrarAcao(e.getStackTrace().toString());
			} catch (Exception e) {
				System.out.println("Erro inesperado");
				e.printStackTrace();
				
				log.registrarAcao("Erro inesperado:");
				log.registrarAcao(e.getStackTrace().toString());
				
				throw new RuntimeException();
			}
		}
	}
	
	private static void carregaAvioes() {
		if(avioes == null) {
			try {
				ArrayList<Voo> voos = (ArrayList<Voo>) gerenciadorVoos.carregaDados();
				log.registrarAcao("Voos carregados com sucesso.");
				
				avioes = new ArrayList<>();
				
				for(int i = 0; i < qtdAvioes; i++) {
					avioes.add(new Aviao("Aviao" + i));
				}
				
				for(Voo voo : voos) {
					if(avioes.contains(voo.getAviao())) {
						int index = avioes.indexOf(voo.getAviao());
						avioes.get(index).addVoo(voo);				
					}
				}
			} catch (IOException e) {
				System.out.println("Erro ao ler voos.");
				e.printStackTrace();
				
				log.registrarAcao("Erro ao ler voos, causa:");
				log.registrarAcao(e.getStackTrace().toString());
			} catch (Exception e) {
				System.out.println("Erro inesperado");
				e.printStackTrace();
				
				log.registrarAcao("Erro inesperado:");
				log.registrarAcao(e.getStackTrace().toString());
				
				throw new RuntimeException();
			}
		}
	}
	
	private static void salvaVoos() throws IOException {
		if(avioes != null) {
			ArrayList<Voo> voos = new ArrayList<>();
			
			for(Aviao aviao : avioes) {
				if(aviao.getVoos() != null) {
					for(Voo voo : aviao.getVoos()) {
						voos.add(voo);
					}
				}
			}
			
			try {
				gerenciadorVoos.salvaDados(voos);
				log.registrarAcao("Voos salvos com sucesso");
			} catch (IOException e) {
				System.out.println("Erro ao salvar voos");
				e.printStackTrace();
				
				log.registrarAcao("Erro ao salvar voos, causa:");
				log.registrarAcao(e.getStackTrace().toString());
				
				throw new IOException(e.getMessage());
			} catch (Exception e) {
				System.out.println("Erro inesperado");
				e.printStackTrace();
				
				log.registrarAcao("Erro inesperado:");
				log.registrarAcao(e.getStackTrace().toString());
				
				throw new RuntimeException();
			}
		}
	}
	
	public static void desliga() {
		String msg;
		msg = "Desconectando.";
		mostraAviso(msg, JOptionPane.INFORMATION_MESSAGE);
		log.registrarAcao("Desligando sistema.");
		
		try {
			salvaUsuarios();
			salvaVoos();
		} catch (IOException e) {
			log.registrarAcao("Sistema desligou com erros.");
		}
		
		log.registrarAcao("Sistema desligou sem erros.");
		
		try {
			log.close();
		} catch(Exception e) {
			msg = "Erro ao fechar log.";
			mostraAviso(msg, JOptionPane.ERROR_MESSAGE);
		}	
	}	
	
	private static Usuario buscaUsuario(String nome) {
		for(Usuario u : users) {
			if(u.getNome().equals(nome)) {
				return u;
			}
		}
		return null;
	}
	
	public static Atendente validaCadastroAtendente(String nome) {
		if (nome.isBlank() || nome.isEmpty()) {
			throw new IllegalArgumentException("Nome inválido!");
		}
		if (buscaUsuario(nome) != null) {
			throw new IllegalArgumentException("Nome já está em uso!");
		}
		return new Atendente(nome);
	}
	
	public static void cadastraAtendente(Atendente atendente) {
		users.add(atendente);
		log.registrarAcao("Novo atendente adicionado: " + atendente.getNome());
	}
	
	public static Passageiro validaCadastroPassageiro(String nome, int idade) {
		if (nome.isBlank() || nome.isEmpty()) {
			throw new IllegalArgumentException("Nome inválido!");
		}
		if (buscaUsuario(nome) != null) {
			throw new IllegalArgumentException("Nome já está em uso!");
		}
		return new Passageiro(nome, idade);
	}
	
	public static void cadastraPassageiro(Passageiro passageiro) {
		users.add(passageiro);
		log.registrarAcao("Novo passageiro adicionado: " + passageiro.getNome());
	}
	
	public static String[] getNomeUsuarios() {
		String[] usuarios = new String[users.size()];
		for(Usuario u : users) {
			if (u instanceof Atendente) {
				usuarios[users.indexOf(u)] = "Atendente: " + u.getNome();
			} else {
				usuarios[users.indexOf(u)] = "Passageiro: " + u.getNome() + " | Idade: " + u.getIdade();
			}
		}
		return usuarios;
	}
	
	public static int getQtdUsuarios() {
		return users.size();
	}
	
	public static void removeUsuario(String nome) {
		Usuario user = buscaUsuario(nome);
		users.remove(user);
		mostraAviso("Usuario removido com sucesso", JOptionPane.PLAIN_MESSAGE);
		log.registrarAcao("Usurario removido: " + user.getNome());
	}
	
	public static boolean validaLogin(String nome) {
		Usuario user = buscaUsuario(nome);
		if (user == null) {
			throw new IllegalArgumentException("Usuário não cadastrado!");
		}
		return true;
	}
	
	public static String fazLogin(String nome) {
		Usuario user = buscaUsuario(nome);
		usuarioAtual = user;
		if (user instanceof Atendente) {
			return "Atendente";
		}
		return "Passageiro";
	}
	
	public static ArrayList<Voo> getVoosAtuais() {
		ArrayList<Voo> voos = new ArrayList<>();
		for(Aviao aviao : avioes) {
			if(aviao.getVoos() != null) {
				voos.addAll(aviao.getVoos());
			}
		}
		return voos;
	}
	
	public static int getQtdVoos() {
		return getVoosAtuais().size();
	}
	
	public static int getQtdAvioes() {
		return avioes.size();
	}
	
	public static ArrayList<Aviao> getAvioesDisponiveis(LocalDate data, LocalTime hora) {
		Voo voo = new Voo(data, hora, "", "", 0f, 0f);
		ArrayList<Aviao> disponiveis = (ArrayList<Aviao>) avioes.stream().filter(a -> a.verificaDisponibilidade(voo)).collect(Collectors.toList());
		return disponiveis;
	}
	
	public static Voo validaCriacaoVoo(String origem, String destino, LocalDate data, LocalTime hora, double precoPrimeiraClasse, double precoClasseEconomica, Aviao aviao) {
		if (data.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Data inválida!");
		}
		if (data == LocalDate.now() && hora.isBefore(LocalTime.now())) {
			throw new IllegalArgumentException("Hora inválida!");
		}
		if (origem.isBlank() || origem.isEmpty()) {
			throw new IllegalArgumentException("Origem inválida!");
		}
		if (destino.isBlank() || destino.isEmpty()) {
			throw new IllegalArgumentException("Destino inválido!");
		}
		if (aviao == null || !avioes.contains(aviao)) {
			throw new IllegalArgumentException("È preciso selecionar um aviao!");
		}
		if (precoPrimeiraClasse < 0) {
			throw new IllegalArgumentException("Preço da primeira classe inválido!");
		}
		if (precoClasseEconomica < 0) {
			throw new IllegalArgumentException("Preço da classe econômica inválido!");
		}
		return new Voo(data, hora, origem, destino, precoPrimeiraClasse, precoClasseEconomica);
	}
	
	public static void criaVoo(Voo voo, Aviao aviao) {
		voo.setAviao(avioes.get(avioes.indexOf(aviao)));
		log.registrarAcao("Novo voo criado: " + voo);
	}
	
	public static void mostraAviso(String message, int type) {
		switch (type) {
			case JOptionPane.ERROR_MESSAGE:
				JOptionPane.showMessageDialog(null, message, "Erro", type);
				break;
			case JOptionPane.PLAIN_MESSAGE:
				JOptionPane.showMessageDialog(null, message, "Sucesso", type);
				break;
			default:
				JOptionPane.showMessageDialog(null, message, "Aviso", JOptionPane.INFORMATION_MESSAGE);
				break;
		}
	}

	public static Usuario getUsuarioAtual() {
		return usuarioAtual;
	}

	public static void setUsuarioAtual(Usuario usuarioAtual) {
		Sistema.usuarioAtual = usuarioAtual;
	}
}
