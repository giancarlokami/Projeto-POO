package principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import aviao.Aviao;
import aviao.Voo;
import exceptions.HorarioIndisponivelException;
import usuario.Atendente;
import usuario.Passageiro;
import usuario.Usuario;

public class Sistema {
	
	public static final int qtdAvioes = 4;
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
			sc = new Scanner(System.in);
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
		menuPrincipal();
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
			} catch (HorarioIndisponivelException e) {
				log.registrarAcao("Voo invalido encontrado no arquivo.");
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
	
	private static void desliga() {
		System.out.println("Desconectando.");
		log.registrarAcao("Desligando sistema.");
		sc.close();
		
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
			System.out.println("Erro ao fechar log.");
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
	
	private static ArrayList<Voo> getVoosAtuais() {
		ArrayList<Voo> voos = new ArrayList<>();
		
		for(Aviao aviao : avioes) {
			if(aviao.getVoos() != null) {
				voos.addAll(aviao.getVoos());
			}
		}
		
		return voos;
	}
	
	private static void mensagemBoasVindas() {
		System.out.println("            ______                                             ");
		System.out.println("            _\\ _~-\\___                                       ");
		System.out.println("    =  = ==(___UFABC__D                                        ");
		System.out.println("                \\_____\\___________________,-~~~~~~~`-.._     ");
		System.out.println("                /     o O o o o o O O o o o o o o O o  |\\_    ");
		System.out.println("                `~-.__        ___..----..                  )   ");
		System.out.println("                      `---~~\\___________/------------`````    ");
		System.out.println("                      =  ===(_________D                        ");
		System.out.println("\nSeja bem vindo(a) a UFABC Airlines.");
	}
	
	private static void menuPrincipal() {
		mensagemBoasVindas();
		int escolha = 0;
		
		while(escolha != 5) {
			System.out.println("\nEscolha uma opcao:");
			System.out.println("|- 1. Cadastrar um novo usuario");
			System.out.println("|- 2. Remover um usuario");
			System.out.println("|- 3. Exibir usuarios cadastrados");
			System.out.println("|- 4. Fazer login (eh necessario para buscar os voos)");
			System.out.println("|- 5. Sair");
			
			escolha = sc.nextInt();
			
			switch(escolha) {
			case 1:
				menuCadastroUsuario();
				break;
			case 2:
				menuRemoveUsuario();
				break;
			case 3:
				if(users.size() == 0) {
					System.out.println("Nao ha usuarios cadastrados");
				} else {
					users.forEach(u -> System.out.println(u));
				}
				break;
			case 4:
				menuLogin();
				break;
			case 5:
				break;
			default:
				System.out.println("Escolha invalida.");
				break;
			}
		}
		
		desliga();
	}

	private static void menuCadastroUsuario() {
		char tipoUser;
		String nomeUser;
		Usuario novo;
		
		System.out.println("Digite o tipo de usuario(A para Atendente, P para passageiro), ou digite 'S' para voltar:");
		tipoUser = sc.next().toUpperCase().charAt(0);
		
		while(tipoUser != 'A' && tipoUser != 'P' && tipoUser != 'S') {
			System.out.println("Opcao invalida, digite A para Atendente, P para passageiro ou 'S' para voltar");
			tipoUser = sc.next().toUpperCase().charAt(0);
		}
		
		if(tipoUser == 'S') {
			return;
		}
		
		System.out.println("Digite nome do usuario, use '-' ou '_' no lugar de espacos, ou 'S' para voltar:");
		nomeUser = sc.next().replaceAll("[_-]", " ");
		
		if(nomeUser.toUpperCase().equals("S")) {
			return;
		}
		
		while(buscaUsuario(nomeUser) != null) {
			System.out.println("Nome ja esta sendo utilizado, digite um nome diferente ou 'S' para voltar");
			nomeUser = sc.next().replaceAll("[_-]", " ");
			
			if(nomeUser.toUpperCase().equals("S")) {
				return;
			}
		}
		
		if(tipoUser == 'A') {
			novo = new Atendente(nomeUser);
		} else {
			novo = new Passageiro(nomeUser);
		}
		
		users.add(novo);
		System.out.println("Usuario adicionado com sucesso");
		log.registrarAcao("Novo usuario adicionado: " + nomeUser);
	}
	
	private static void menuRemoveUsuario() {
		System.out.println("Digite o nome do usuario que sera removido, use '-' ou '_' no lugar de espacos, ou 'S' para voltar:");
		String nome = sc.next().replaceAll("[_-]", " ");
		
		if(nome.toUpperCase().equals("S")) {
			return;
		}
		
		Usuario user = buscaUsuario(nome);
		
		while(user == null) {
			System.out.println("Nao existe nenhum usuario com esse nome, digite um nome diferente ou 'S' para voltar");
			nome = sc.next().replaceAll("[_-]", " ");
			
			if(nome.toUpperCase().equals("S")) {
				return;
			}
		}
		
		users.remove(user);
		System.out.println("Usuario removido com sucesso");
		log.registrarAcao("Usurario removido: " + nome);
		
	}
	
	private static void menuLogin() {
		String nome = "";
		
		while(nome != null && !nome.toUpperCase().equals("S")) {
			System.out.println("Digite o seu nome, use '-' ou '_' no lugar de espacos, ou digite 'S' para voltar:");
			nome = sc.next().replaceAll("[_-]", " ");
			Usuario user = buscaUsuario(nome);
			
			if(!nome.toUpperCase().equals("S") && user != null) {
				if(user instanceof Atendente) {
					menuAtendente((Atendente) user);
					return;
				} else {
					menuPassageiro(user);
					return;
				}
			} else if (!nome.toUpperCase().equals("S")) {
				System.out.println("Usuario nao cadastrado.");
			}
		}
	}
	
	private static void menuPassageiro(Usuario usuario) {
		System.out.println("Seja bem vindo(a) " + usuario.getNome());
		int escolha = 0;
		
		while(escolha != 6) {
			System.out.println("Escolha uma opcao:");
			System.out.println("|- 1. Consultar reservas");
			System.out.println("|- 2. Cancelar reserva");
			System.out.println("|- 3. Pagar reserva");
			System.out.println("|- 4. Procurar voo");
			System.out.println("|- 5. Alterar nome");
			System.out.println("|- 6. Voltar");
			
			escolha = sc.nextInt();
			
			switch(escolha) {
			case 1:
				/*if(usuario.getReservas() == null || usuario.getReservas().size() == 0) {
					System.out.println("O usuario nao possui reservas");
				} else {
					usuario.getReservas().forEach(reserva -> System.out.println(reserva));
				}
				*/
				break;
			case 2:
				//menuCancelaReserva(usuario);
				break;
			case 3:
				//menuPagaReserva(usuario);
				break;
			case 4:
				menuBuscaVoo(usuario);
				break;
			case 5:
				menuMudaNome(usuario);
				break;
			case 6:
				break;
			default:
				System.out.println("Escolha invalida.");
				break;
			}
		}
	}
	
	private static void menuAtendente(Atendente atendente) {
		System.out.println("Seja bem vindo(a) " + atendente.getNome());
		int escolha = 0;
		
		while(escolha != 9) {
			System.out.println("Escolha uma opcao:");
			System.out.println("|- 1. Consultar reservas");
			System.out.println("|- 2. Cancelar reserva");
			System.out.println("|- 3. Pagar reserva");
			System.out.println("|- 4. Procurar voo");
			System.out.println("|- 5. Alterar nome");
			System.out.println("|- 6. Criar voo");
			System.out.println("|- 7. Cancelar voo");
			System.out.println("|- 8. Gerar relatorio de um voo");
			System.out.println("|- 9. Voltar");
			
			escolha = sc.nextInt();
			
			switch(escolha) {
			case 1:
				/*if(atendente.getReservas() == null || atendente.getReservas().size() == 0) {
					System.out.println("O usuario nao possui reservas");
				} else {
					atendente.getReservas().forEach(reserva -> System.out.println(reserva));
				}
				*/
				break;
			case 2:
				//menuCancelaReserva(atendente);
				break;
			case 3:
				//menuPagaReserva(atendente);
				break;
			case 4:
				menuBuscaVoo(atendente);
				break;
			case 5:
				menuMudaNome(atendente);
				break;
			case 6:
				atendente.criaVoo(avioes);				
				break;
			case 7:
				atendente.cancelaVoo(getVoosAtuais());
				break;
			case 8:
				atendente.geraRelatorio(getVoosAtuais());
				break;
			case 9:
				break;
			default:
				System.out.println("Escolha invalida.");
				break;
			}
		}
	}
	/*
	private static void menuCancelaReserva(Usuario usuario) {
		ArrayList<Reserva> reservas = usuario.getReservas();
		
		if(reservas != null && reservas.size() > 0) {
			int escolha;
			
			System.out.println("Escolha uma reserva para cancelar ou digite -1 para sair:");
			
			for(int i = 0; i < reservas.size(); i++) {
				System.out.println((i + 1) + "" + reservas.get(i));
			}
			
			escolha = sc.nextInt();
			
			while(escolha < -1 || escolha >= reservas.size()) {
				System.out.println("Escolha algo entre 1 e " + reservas.size());
				escolha = sc.nextInt();
			}
			
			if(escolha == -1) {
				return;
			}
			
			usuario.cancelaReserva(reservas.get(escolha - 1));
		} else {
			System.out.println("O usuario nao possui reservas");
		}
	}
	
	private static void menuPagaReserva(Usuario usuario) {
		ArrayList<Reserva> reservas = usuario.getReservas();
		
		if(reservas != null && reservas.size() > 0) {
			int escolha;
			
			System.out.println("Escolha uma reserva para pagar ou digite -1 para sair:");
			
			for(int i = 0; i < reservas.size(); i++) {
				System.out.println((i + 1) + "" + reservas.get(i));
			}
			
			escolha = sc.nextInt();
			
			while(escolha < -1 || escolha >= reservas.size()) {
				System.out.println("Escolha algo entre 1 e " + reservas.size());
				escolha = sc.nextInt();
			}
			
			if(escolha == -1) {
				return;
			}
			
			usuario.pagaReserva(reservas.get(escolha));
		} else {
			System.out.println("O usuario nao possui reservas");
		}
	}*/
	
	private static void menuBuscaVoo(Usuario usuario) {
		System.out.println("Digite um destino, use '_' ou '-' no lugar de espacos, ou 'S' para voltar: ");
		String destino = sc.next().replaceAll("[_-]", " ");
		final String destinoConst = destino;
		
		if(destino.toUpperCase().equals("S")) {
			return;
		}
		
		ArrayList<Voo> voosAtuais = getVoosAtuais();
		ArrayList<Voo> matches = (ArrayList<Voo>) voosAtuais.stream()
				.filter(voo -> voo.getDestino().equals(destinoConst))
				.collect(Collectors.toList());
		
		while(matches.size() == 0) {
			System.out.println("Nao ha voos para esse destino. Digite um novo destino para continuar buscando ou 'S' para sair");
			destino = sc.next().replaceAll("[_-]", " ");
			final String destinoFinal = destino;
			
			if(destino.toUpperCase().equals("S")) {
				return;
			}
			
			matches = (ArrayList<Voo>) voosAtuais.stream()
					.filter(voo -> voo.getDestino().equals(destinoFinal))
					.collect(Collectors.toList());
		}
		
		System.out.println("Os voos disponiveis para esse destino sao, Escolha um, ou digite -1 para sair:");
		
		for(int i = 0; i < matches.size(); i++) {
			System.out.println(i + " - " + matches.get(i));
		}
		
		int escolha = sc.nextInt();
		
		while(escolha < -1 || escolha >= matches.size()) {
			System.out.println("Escolha algo entre 1 e " + matches.size());
			escolha = sc.nextInt();
		}
		
		if(escolha == -1) {
			return;
		}
		
		usuario.reservaVoo(matches.get(escolha));
	}
	
	private static void menuMudaNome(Usuario usuario) {
		System.out.println("Digite um novo nome, use '-' ou '_' no lugar de espacos, ou 'S' para voltar:");
		String novoNome = sc.next().replaceAll("[_-]", " ");
		
		if(novoNome.toUpperCase().equals("S")) {
			return;
		}
		
		while(buscaUsuario(novoNome) != null) {
			System.out.println("Nome ja esta sendo utilizado, digite um nome diferente ou 'S' para voltar)");
			novoNome = sc.next().replaceAll("[_-]", " ");
			
			if(novoNome.toUpperCase().equals("S")) {
				return;
			}
		}
		
		usuario.setNome(novoNome);
	}
}
