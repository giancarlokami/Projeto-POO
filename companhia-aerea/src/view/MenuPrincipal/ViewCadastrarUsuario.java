package view.MenuPrincipal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Sistema;
import usuario.Atendente;
import usuario.Passageiro;

import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ViewCadastrarUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JRadioButton rdbtnAtendente;
	private JRadioButton rdbtnPassageiro;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panel_1;
	private JTextField txtNome;
	private JLabel lblNome;
	private JLabel lblIdade;
	private JTextField txtIdade;
	private JPanel panel_2;
	private JButton btnCadastrarUsuario;
	private JButton btnSair;

	public ViewCadastrarUsuario() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		setTitle("Cadastrar Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 300);
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 0, 412, 31);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		rdbtnAtendente = new JRadioButton("Atendente");
		rdbtnAtendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblIdade.setEnabled(false);
				txtIdade.setEnabled(false);
			}
		});
		rdbtnAtendente.setSelected(true);
		rdbtnAtendente.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonGroup.add(rdbtnAtendente);
		rdbtnAtendente.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(rdbtnAtendente);
		
		rdbtnPassageiro = new JRadioButton("Passageiro");
		rdbtnPassageiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblIdade.setEnabled(true);
				txtIdade.setEnabled(true);
			}
		});
		rdbtnPassageiro.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonGroup.add(rdbtnPassageiro);
		rdbtnPassageiro.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(rdbtnPassageiro);
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 43, 412, 97);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblNome = new JLabel("Nome:");
		lblNome.setBounds(12, 12, 53, 27);
		panel_1.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(73, 12, 327, 28);
		panel_1.add(txtNome);
		txtNome.setColumns(10);
		
		lblIdade = new JLabel("Idade:");
		lblIdade.setEnabled(false);
		lblIdade.setBounds(12, 55, 53, 27);
		panel_1.add(lblIdade);
		
		txtIdade = new JTextField();
		txtIdade.setEnabled(false);
		txtIdade.setColumns(10);
		txtIdade.setBounds(73, 55, 60, 28);
		panel_1.add(txtIdade);
		
		panel_2 = new JPanel();
		panel_2.setBounds(12, 152, 412, 106);
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 20));
		
		btnCadastrarUsuario = new JButton("Cadastrar Usuario");
		btnCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (buttonGroup.getSelection() == rdbtnAtendente.getModel()) {
					try {
						String nome = txtNome.getText();
						Atendente atendente = Sistema.validaCadastroAtendente(nome);
						Sistema.cadastraAtendente(atendente);
						Sistema.mostraAviso("Atendente cadastrado com sucesso!", JOptionPane.PLAIN_MESSAGE);
					} catch (Exception e) {
						Sistema.mostraAviso(e.getMessage(), JOptionPane.ERROR_MESSAGE);
					}
				} else if (buttonGroup.getSelection() == rdbtnPassageiro.getModel()) {
					try {
						String nome = txtNome.getText();
						int idade = Integer.parseInt(txtIdade.getText());
						Passageiro passageiro = Sistema.validaCadastroPassageiro(nome, idade);
						Sistema.cadastraPassageiro(passageiro);
						Sistema.mostraAviso("Passageiro cadastrado com sucesso!", JOptionPane.PLAIN_MESSAGE);
					} catch (Exception e) {
						Sistema.mostraAviso(e.getMessage(), JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		panel_2.add(btnCadastrarUsuario);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		panel_2.add(btnSair);
	}
	
	private void sair() {
		new ViewMenuPrincipal().setVisible(true);
		dispose();
	}
}
