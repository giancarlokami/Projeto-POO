package principal;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import aviao.Aviao;
import aviao.Poltrona;
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
	private static Usuario usuarioAtual = null;
	private static Voo vooAtual = null;
	
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
		if (idade<12||idade>120)
			throw new IllegalArgumentException("Idade inv�lida!"); 
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
	
	public static ArrayList<Voo> getVoosFiltrados(String[] filtro){
		String dataS = filtro[0];
		String horaS = filtro[1];
		String origem = filtro[2];
		String destino = filtro[3];
		LocalDate data;
		LocalTime hora;
		ArrayList<Voo> voosFiltrados = getVoosAtuais();
		if (!dataS.replace("/", "").isBlank() && !dataS.replace("/", "").isEmpty()){
			try {
				data = LocalDate.parse(dataS, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				voosFiltrados = (ArrayList<Voo>) voosFiltrados.stream().filter(v -> v.getData().equals(data)).collect(Collectors.toList());
			} catch (Exception e) {
				throw new IllegalArgumentException("Data inválida");
			}
		}
		if (!horaS.replace(":", "").isBlank() && !horaS.replace(":", "").isEmpty()){
			try {
				hora = LocalTime.parse(horaS, DateTimeFormatter.ofPattern("HH:mm"));
				voosFiltrados = (ArrayList<Voo>) voosFiltrados.stream().filter(v -> v.getHora().equals(hora)).collect(Collectors.toList());
			} catch (Exception e) {
				throw new IllegalArgumentException("Hora inválida");
			}
		}
		if (!origem.isBlank() && !origem.isEmpty()) {
			voosFiltrados = (ArrayList<Voo>) voosFiltrados.stream().filter(v -> v.getOrigem().equals(origem)).collect(Collectors.toList());
		}
		if (!destino.isBlank() && !destino.isEmpty()) {
			voosFiltrados = (ArrayList<Voo>) voosFiltrados.stream().filter(v -> v.getDestino().equals(destino)).collect(Collectors.toList());
		}
		return voosFiltrados;
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
		if(LocalDate.now().until(data).getYears()>20) {
			throw new IllegalArgumentException("Data inválida!");
		}
			
		return new Voo(data, hora, origem, destino, precoPrimeiraClasse, precoClasseEconomica);
	}
	
	public static void criaVoo(Voo voo, Aviao aviao) {
		voo.setAviao(avioes.get(avioes.indexOf(aviao)));
		log.registrarAcao("Novo voo criado: " + voo);
	}
	
	public static void validaAlteracaoDeNome(String nome) {
		if (nome.isBlank() || nome.isEmpty()) {
			throw new IllegalArgumentException("Nome inválido!");
		}
		if (users.stream().filter(u -> u.getNome().contentEquals(nome)).collect(Collectors.toList()).size() != 0) {
			throw new IllegalArgumentException("Já existe um usuário com este nome!");
		}
	}
	
	public static void alteraNome(String nome) {
		usuarioAtual.setNome(nome);
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
	
	public static Voo getVooFromString(String string) {
		for (Voo v: getVoosAtuais()) {
			if (v.toString().contentEquals(string)) {
				return v;
			}
		}
		throw new NoSuchElementException("Voo não encontrado!");
	}

	public static void setUsuarioAtual(Usuario usuarioAtual) {
		Sistema.usuarioAtual = usuarioAtual;
	}
	
	public static void setVooAtual(Voo voo) {
		vooAtual = voo;
	}
	
	public static Voo getVooAtual() {
		return vooAtual;
	}
	
	public static String getInfoVoo(Voo voo) {
		String info = String.format(
				  "Data: %s | Hora: %s\n"
				+ "Origem: %s | Destino: %s\n"
				+ "Aviao: %s\n"
				+ "Preco Primeira Classe: R$ %.2f\n"
				+ "Preco Classe Economica: R$ %.2f\n"
				, voo.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
				, voo.getHora()
				, voo.getOrigem()
				, voo.getDestino()
				, voo.getAviao()
				, voo.getPrecoPrimeiraClasse()
				, voo.getPrecoClasseEconomica());
		return info;
	}
	
	public static String getInfoReserva() {
		String info = String.format(
				  "Poltronas Selecionadas: %s\n"
				+ "Poltronas da Primeira Classe: %d\n"
				+ "Poltronas da Classe Economica: %d\n"
				+ "Total: R$ %.2f"
				, getInfoPoltronasUsuario()
				, getQtdPoltronasPrimeiraClasseSelecionadas()
				, getQtdPoltronasClasseEconomicaSelecionadas()
				, calculaTotalReserva());
		return info;
				
	}
	
	private static String getInfoPoltronasUsuario() {
		String polt = "";
		Collections.sort(getPoltronasUsuario(), new Comparator<Poltrona>() {
			public int compare(Poltrona p1, Poltrona p2) {
				return p1.getNumero() - p2.getNumero();
			}
		});
		
		for (Poltrona p : getPoltronasUsuario()) {
			polt += " " + p.getNumero() + ";";
		}
		return polt;
	}
	
	private static double calculaTotalReserva() {
		return (getVooAtual().getPrecoPrimeiraClasse() * getQtdPoltronasPrimeiraClasseSelecionadas()) +
				(getVooAtual().getPrecoClasseEconomica() * getQtdPoltronasClasseEconomicaSelecionadas());
	}
	
	public static ArrayList<Voo> getReservasUsuarioAtual() {
		ArrayList<Voo> reservas = new ArrayList<Voo>();
		for (Voo v : getVoosAtuais()) {
			for (Poltrona p : v.getPoltronas()) {
				if (p.getUsuario() != null && p.getUsuario().equals(getUsuarioAtual())) {
					reservas.add(v);
				}
			}
		}
		return (ArrayList<Voo>) reservas.stream().distinct().collect(Collectors.toList());
	}
	
	private static int getQtdPoltronasPrimeiraClasseSelecionadas() {
		return getPoltronasUsuario().stream().filter(p -> p.ehPrimeiraClasse()).collect(Collectors.toList()).size();
	}
	
	private static int getQtdPoltronasClasseEconomicaSelecionadas() {
		return getPoltronasUsuario().stream().filter(p -> !p.ehPrimeiraClasse()).collect(Collectors.toList()).size();
	}
	
	public static void validaCancelamentoDeReserva(String reserva) {
		if (reserva.isEmpty() || reserva.isBlank()) {
			throw new IllegalArgumentException("Selecione uma reserva para cancelar!");
		}
		try {
			getVooFromString(reserva);
		} catch (Exception e) {
			throw new NoSuchElementException("Este voo não está cadastrado!");
		}
	}
	
	public static void cancelaReserva(String reserva) {
		Voo v = getVooFromString(reserva);
		for (Poltrona p : v.getPoltronas()) {
			if (p.getUsuario() != null && p.getUsuario().equals(usuarioAtual)) {
				p.cancela();
			}
		}
	}
	
	public static Voo validaReservaAtual(String voo) {
		if (voo.isEmpty() || voo.isBlank()) {
			throw new IllegalArgumentException("Selecione um voo!");
		}
		try {
			return getVooFromString(voo);
		} catch (Exception e) {
			throw new NoSuchElementException("Voo não cadastrado!");
		}
	}

	public static Voo validaRelatorioDeVoo(String voo) {
		Voo v = null;
		try {
			v = getVooFromString(voo);
		} catch (Exception e) {
			throw new NoSuchElementException("Este voo não está cadastrado!");
		}
		if (voo.isBlank() && voo.isEmpty()) {
			throw new IllegalArgumentException("Selecione um voo!");
		}
		return v;
	}
	
	public static String geraRelatorioDeVoo(Voo v) {
		String relatorio = String.format(
				  "Data: %s | Hora: %s\n"
				+ "Origem: %s\n"
				+ "Destino: %s\n"
				+ "Aviao: %s\n"
				+ "\n"
				+ "Dados dos passageiros:\n"
				, v.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
				, v.getHora()
				, v.getOrigem()
				, v.getDestino()
				, v.getAviao());
		for (Poltrona p : v.getPoltronas()) {
			if (p.estaOcupada()) {
				relatorio += String.format("Poltrona %d (%s): %s\n", p.getNumero(), p.getTipo(), p.getUsuario());
			}
		}
		relatorio += String.format(
				  "\nQuantidade de poltronas da primeira classe: %d\n"
				+ "Quantidade de poltronas da classe economica: %d\n"
				+ "Quantidade de poltronas livres: %d\n"
				+ "Quantidade de passageiros menores: %d\n"
				+ "Total de vendas em passagens: R$ %.2f"
				, v.getQtdPoltronasPrimeiraClasseOcupadas()
				, v.getQtdPoltronasClasseEconomicaOcupadas()
				, v.getQtdPoltronasLivres()
				, v.getQtdPassageirosMenores()
				, v.getValorTotal());
		
		return relatorio;
	}
	
	public static Voo validaCancelamentoDeVoo(String voo) {
		if (voo.isBlank() || voo.isEmpty()) {
			throw new IllegalArgumentException("É necessário selecionar um voo!");
		}
		try {
			getVooFromString(voo);
			if (getVooFromString(voo).getQtdPassageirosABordo() < 10) {
				return getVooFromString(voo);
			} else {
				throw new IllegalArgumentException("Não é possível cancelar um voo com mais de 10 reservas!");
			}
		} catch (Exception e) {
			throw new NoSuchElementException("Voo não cadastrado!");
		}
	}
	
	public static void cancelaVoo(Voo voo) {
		avioes.get(avioes.indexOf(voo.getAviao())).removeVoo(voo);
		log.registrarAcao("Voo cancelado: " + voo);
	}
	
	public static ArrayList<Poltrona> getPoltronasUsuario() {
		return (ArrayList<Poltrona>) vooAtual.getPoltronas().stream().filter(p -> p.getUsuario() != null && p.getUsuario().equals(getUsuarioAtual())).collect(Collectors.toList());
	}
	
	public static void setPoltronasUsuario(ArrayList<Poltrona> poltronas) {
		for (Poltrona p: poltronas) {
			getUsuarioAtual().reservaPoltrona(p);
		}
	}
}