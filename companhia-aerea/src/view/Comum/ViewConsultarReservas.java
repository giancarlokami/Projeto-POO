package view.Comum;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import aviao.Voo;
import principal.Sistema;
import usuario.Atendente;
import view.MenuAtendente.ViewMenuAtendente;
import view.MenuPassageiro.ViewMenuPassageiro;

public class ViewConsultarReservas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnSair;
	private JButton btnSelecionarReserva;
	private JScrollPane scrollPane;
	private JLabel label;
	private JList<String> lstReservas;
	private DefaultListModel<String> model;

	public ViewConsultarReservas() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		setTitle("Consultar Reservas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 322);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 241, 416, 39);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 20, 0));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		
		btnSelecionarReserva = new JButton("Selecionar Reserva");
		btnSelecionarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String reserva = lstReservas.getSelectedValue().toString();
					Voo voo = Sistema.validaReservaAtual(reserva);
					Sistema.setVooAtual(voo);
					new ViewInformacoesDaReserva().setVisible(true);
					dispose();
				} catch (Exception e) {
					Sistema.mostraAviso(e.getMessage(), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnSelecionarReserva);
		panel.add(btnSair);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 28, 416, 201);
		contentPane.add(scrollPane);
		
		model = new DefaultListModel<String>();
		model.addAll(Sistema.getReservasUsuarioAtual().stream().map(v -> v.toString()).collect(Collectors.toList()));
		lstReservas = new JList<String>();
		lstReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstReservas.setModel(model);
		scrollPane.setViewportView(lstReservas);
		
		label = new JLabel("Suas reservas:");
		label.setBounds(12, 12, 141, 15);
		contentPane.add(label);
	}
	
	private void sair() {
		if (Sistema.getUsuarioAtual() instanceof Atendente) {
			new ViewMenuAtendente().setVisible(true);
		} else {
			new ViewMenuPassageiro().setVisible(true);
		}
		dispose();
	}
}
