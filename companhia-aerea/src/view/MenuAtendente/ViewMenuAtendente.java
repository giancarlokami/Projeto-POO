package view.MenuAtendente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Sistema;
import view.Comum.ViewAlterarNome;
import view.Comum.ViewCancelarReserva;
import view.Comum.ViewConsultarReservas;
import view.Comum.ViewPagarReserva;
import view.Comum.ViewProcurarVoo;
import view.MenuPrincipal.ViewMenuPrincipal;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;

public class ViewMenuAtendente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnConsultarReservas;
	private JButton btnCancelarReserva;
	private JButton btnPagarReserva;
	private JButton btnProcurarVoo;
	private JButton btnAlterarNome;
	private JButton btnCancelarVoo;
	private JButton btnGerarRelatorioDeVoo;
	private JButton btnSair;
	private JButton btnCriarVoo;
	private JPanel panel;
	private JLabel lblBemVindo;

	public ViewMenuAtendente() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		setTitle("Menu Atendente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 40, 416, 318);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 5));
		
		btnConsultarReservas = new JButton("Consultar Reservas");
		panel.add(btnConsultarReservas);
		
		btnCancelarReserva = new JButton("Cancelar Reserva");
		panel.add(btnCancelarReserva);
		
		btnPagarReserva = new JButton("Pagar Reserva");
		panel.add(btnPagarReserva);
		
		btnCriarVoo = new JButton("Criar Voo");
		panel.add(btnCriarVoo);
		
		btnProcurarVoo = new JButton("Procurar Voo");
		panel.add(btnProcurarVoo);
		
		btnCancelarVoo = new JButton("Cancelar Voo");
		panel.add(btnCancelarVoo);
		
		btnGerarRelatorioDeVoo = new JButton("Gerar Relatorio de Voo");
		panel.add(btnGerarRelatorioDeVoo);
		
		btnAlterarNome = new JButton("Alterar Nome");
		panel.add(btnAlterarNome);
		
		btnSair = new JButton("Sair");
		panel.add(btnSair);
		
		lblBemVindo = new JLabel("Bem-vindo(a), " + Sistema.getUsuarioAtual().getNome());
		lblBemVindo.setBounds(12, 12, 404, 15);
		contentPane.add(lblBemVindo);
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		btnAlterarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewAlterarNome().setVisible(true);
				dispose();
			}
		});
		btnGerarRelatorioDeVoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewGerarRelatorioDeVoo().setVisible(true);
				dispose();
			}
		});
		btnCancelarVoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewCancelarVoo().setVisible(true);
				dispose();
			}
		});
		btnProcurarVoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewProcurarVoo().setVisible(true);
				dispose();
			}
		});
		btnCriarVoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewCriarVoo().setVisible(true);
				dispose();
			}
		});
		btnPagarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewPagarReserva().setVisible(true);
				dispose();
			}
		});
		btnCancelarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewCancelarReserva().setVisible(true);
				dispose();
			}
		});
		btnConsultarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewConsultarReservas().setVisible(true);
				dispose();
			}
		});
	}
	private void sair() {
		new ViewMenuPrincipal().setVisible(true);
		dispose();
	}
}
