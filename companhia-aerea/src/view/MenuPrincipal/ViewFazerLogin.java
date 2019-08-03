package view.MenuPrincipal;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Sistema;
import view.MenuAtendente.ViewMenuAtendente;
import view.MenuPassageiro.ViewMenuPassageiro;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewFazerLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNome;
	private JTextField txtNome;
	private JPanel panel_1;
	private JButton btnSair;
	private JButton btnFazerLogin;

	public ViewFazerLogin() {
		setResizable(false);
		setTitle("Fazer Login");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 233);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 0, 416, 64);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 27, 66, 25);
		panel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(75, 27, 311, 25);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 76, 416, 111);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 20));
		
		btnFazerLogin = new JButton("Fazer Login");
		btnFazerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = txtNome.getText();
				String tipo;
				try {
					Sistema.validaLogin(nome);
					tipo = Sistema.fazLogin(nome);
					Sistema.mostraAviso("Logado com sucesso!", JOptionPane.PLAIN_MESSAGE);
					if (tipo == "Atendente") {
						new ViewMenuAtendente().setVisible(true);
						dispose();
					} else {
						new ViewMenuPassageiro().setVisible(true);
						dispose();
					}
				} catch (Exception e) {
					Sistema.mostraAviso(e.getMessage(), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_1.add(btnFazerLogin);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		panel_1.add(btnSair);
	}
	
	private void sair() {
		new ViewMenuPrincipal().setVisible(true);
		dispose();
	}
}
