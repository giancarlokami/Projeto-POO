package view.Comum;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Sistema;
import usuario.Atendente;
import view.MenuAtendente.ViewMenuAtendente;
import view.MenuPassageiro.ViewMenuPassageiro;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;

public class ViewCancelarReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnCancelarReserva;
	private JButton btnSair;
	private JScrollPane scrollPane;
	private JList<String> lstReservas;
	private DefaultListModel<String> model;
	private JLabel lblSuasReservas;
	
	public ViewCancelarReserva() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		setTitle("Cancelar Reserva");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 199, 426, 59);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 20, 0));
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		
		btnCancelarReserva = new JButton("Cancelar Reserva");
		btnCancelarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String reserva = lstReservas.getSelectedValue().toString();
					Sistema.validaCancelamentoDeReserva(reserva);
					Sistema.cancelaReserva(reserva);
					model.remove(lstReservas.getSelectedIndex());
					Sistema.mostraAviso("Reserva cancelada com sucesso!", JOptionPane.PLAIN_MESSAGE);
				} catch (Exception e) {
					Sistema.mostraAviso(e.getMessage(), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnCancelarReserva);
		panel.add(btnSair);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 24, 416, 163);
		contentPane.add(scrollPane);
		
		model = new DefaultListModel<String>();
		model.addAll(Sistema.getReservasUsuarioAtual().stream().map(v -> v.toString()).collect(Collectors.toList()));
		lstReservas = new JList<String>();
		lstReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstReservas.setModel(model);
		scrollPane.setViewportView(lstReservas);
		
		lblSuasReservas = new JLabel("Suas reservas:");
		lblSuasReservas.setBounds(12, 0, 141, 15);
		contentPane.add(lblSuasReservas);
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
