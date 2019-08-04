package view.MenuAtendente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import aviao.Voo;
import principal.Sistema;

import javax.swing.JScrollPane;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;

public class ViewCancelarVoo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnCancelarVoo;
	private JButton btnSair;
	private JList<String> lstVoos;

	public ViewCancelarVoo() {
		setResizable(false);
		setTitle("Cancelar Voo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 416, 193);
		contentPane.add(scrollPane);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addAll(Sistema.getVoosAtuais().stream().map(v -> v.toString()).collect(Collectors.toList()));
		lstVoos = new JList<String>();
		lstVoos.setModel(model);
		lstVoos.setToolTipText("Lista de Voos");
		lstVoos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(lstVoos);
		
		panel = new JPanel();
		panel.setBounds(12, 217, 416, 41);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 20, 0));
		
		btnCancelarVoo = new JButton("Cancelar Voo");
		btnCancelarVoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Voo v = Sistema.validaCancelamentoDeVoo(lstVoos.getSelectedValue().toString());
					Sistema.cancelaVoo(v);
					model.remove(lstVoos.getSelectedIndex());
					Sistema.mostraAviso("Voo cancelado com sucesso!", JOptionPane.PLAIN_MESSAGE);
				} catch (Exception e) {
					Sistema.mostraAviso("É preciso selecionar um voo!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnCancelarVoo);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		panel.add(btnSair);
	}
	
	private void sair() {
		new ViewMenuAtendente().setVisible(true);
		dispose();
	}
}
