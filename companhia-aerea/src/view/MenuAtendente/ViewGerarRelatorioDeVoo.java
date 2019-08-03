package view.MenuAtendente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Sistema;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.GridLayout;
import java.util.stream.Collectors;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewGerarRelatorioDeVoo extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblSelecioneUmVoo;
	private JScrollPane scrollPane;
	private JList<String> lstVoos;
	private JPanel panel_1;
	private JButton btnGerarRelatorioDeVoo;
	private JButton btnSair;

	public ViewGerarRelatorioDeVoo() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		setTitle("Gerar Relatorio de Voo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 456, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 12, 426, 202);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblSelecioneUmVoo = new JLabel("Selecione um voo para gerar um relatorio:");
		lblSelecioneUmVoo.setHorizontalAlignment(SwingConstants.LEFT);
		lblSelecioneUmVoo.setBounds(12, 12, 402, 15);
		panel.add(lblSelecioneUmVoo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 39, 402, 151);
		panel.add(scrollPane);
		
		lstVoos = new JList<String>();
		lstVoos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(lstVoos);
		DefaultListModel<String> model = new DefaultListModel<String>();
		if (Sistema.getQtdVoos() > 0) {
			model.addAll(Sistema.getVoosAtuais().stream().map(v -> v.toString()).collect(Collectors.toList()));
		}
		lstVoos.setModel(model);
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 226, 426, 32);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 20, 0));
		
		btnGerarRelatorioDeVoo = new JButton("Gerar Relatorio de Voo");
		panel_1.add(btnGerarRelatorioDeVoo);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		panel_1.add(btnSair);
	}
	
	private void sair() {
		new ViewMenuAtendente().setVisible(true);
		dispose();
	}
}
