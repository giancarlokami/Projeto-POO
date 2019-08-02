package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Sistema;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;

public class ViewMenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCadastrarUsuario;
	private JButton btnRemoverUsuario;
	private JButton btnExibirUsuarios;
	private JButton btnFazerLogin;
	private JButton btnSair;

	public ViewMenuPrincipal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		setTitle("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new GridLayout(0, 1, 0, 20));
		
		this.btnCadastrarUsuario = new JButton("Cadastrar Usuario");
		btnCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewCadastrarUsuario().setVisible(true);
				dispose();
			}
		});
		this.contentPane.add(this.btnCadastrarUsuario);
		
		this.btnRemoverUsuario = new JButton("Remover Usuario");
		btnRemoverUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewRemoverUsuario().setVisible(true);
				dispose();
			}
		});
		this.contentPane.add(this.btnRemoverUsuario);
		
		this.btnExibirUsuarios = new JButton("Exibir Usuarios");
		btnExibirUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewExibirUsuarios().setVisible(true);
				dispose();
			}
		});
		this.contentPane.add(this.btnExibirUsuarios);
		
		this.btnFazerLogin = new JButton("Fazer Login");
		btnFazerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewFazerLogin().setVisible(true);
				dispose();
			}
		});
		this.contentPane.add(this.btnFazerLogin);
		
		this.btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		this.contentPane.add(this.btnSair);
	}
	
	private void sair() {
		Sistema.desliga();
		dispose();
	}
}
