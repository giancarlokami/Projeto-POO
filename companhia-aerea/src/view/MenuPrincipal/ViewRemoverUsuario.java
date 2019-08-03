package view.MenuPrincipal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Sistema;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ViewRemoverUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblUsurios;
	private JPanel panel_1;
	private JButton btnSair;
	private JButton btnRemoverUsuario;
	private JScrollPane scrollPane;
	private JList<String> lstUsuarios;

	public ViewRemoverUsuario() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		setTitle("Remover Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 0, 416, 212);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblUsurios = new JLabel("Usuários:");
		lblUsurios.setBounds(0, 0, 81, 26);
		panel.add(lblUsurios);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 28, 392, 172);
		panel.add(scrollPane);
		
		lstUsuarios = new JList<String>();
		lstUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultListModel<String> model = new DefaultListModel<String>();
		if (Sistema.getQtdUsuarios() > 0) {
			model.addAll(Arrays.asList(Sistema.getNomeUsuarios()));
		}
		lstUsuarios.setModel(model);
		scrollPane.setViewportView(lstUsuarios);
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 224, 416, 34);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 20, 0));
		
		btnRemoverUsuario = new JButton("Remover Usuario");
		btnRemoverUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (lstUsuarios.getSelectedIndex() != -1) {
					String user = lstUsuarios.getSelectedValue().toString();
					if (user.contains("Atendente")) {
						user = user.substring(user.indexOf(": ") + 2, user.length());
					} else {
						user = user.substring(user.indexOf(": ") + 2, user.indexOf(" |"));
					}
					Sistema.removeUsuario(user);
					model.remove(lstUsuarios.getSelectedIndex());
				} else {
					Sistema.mostraAviso("Nenhum usuario selecionado!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_1.add(btnRemoverUsuario);
		
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
