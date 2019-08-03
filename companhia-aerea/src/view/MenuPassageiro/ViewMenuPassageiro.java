package view.MenuPassageiro;

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
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewMenuPassageiro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnConsultarReservas;
	private JButton btnCancelarReserva;
	private JButton btnPagarReserva;
	private JButton btnProcurarVoo;
	private JButton btnAlterarNome;
	private JButton btnSair;
	private JPanel panel;
	private JLabel label;

	public ViewMenuPassageiro() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		setTitle("Menu Passageiro");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 359);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 43, 421, 274);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 10));
		
		btnConsultarReservas = new JButton("Consultar Reservas");
		btnConsultarReservas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewConsultarReservas().setVisible(true);
				dispose();
			}
		});
		panel.add(btnConsultarReservas);
		
		btnPagarReserva = new JButton("Pagar Reserva");
		btnPagarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewPagarReserva().setVisible(true);
				dispose();
			}
		});
		panel.add(btnPagarReserva);
		
		btnCancelarReserva = new JButton("Cancelar Reserva");
		btnCancelarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewCancelarReserva().setVisible(true);
				dispose();
			}
		});
		panel.add(btnCancelarReserva);
		
		btnProcurarVoo = new JButton("Procurar Voo");
		btnProcurarVoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewProcurarVoo().setVisible(true);
				dispose();
			}
		});
		panel.add(btnProcurarVoo);
		
		btnAlterarNome = new JButton("Alterar Nome");
		btnAlterarNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewAlterarNome().setVisible(true);
				dispose();
			}
		});
		panel.add(btnAlterarNome);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		panel.add(btnSair);
		
		label = new JLabel("Bem-vindo(a), " + Sistema.getUsuarioAtual().getNome());
		label.setBounds(17, 12, 404, 15);
		contentPane.add(label);
	}
	
	private void sair() {
		new ViewMenuPrincipal().setVisible(true);
		dispose();
	}
}
