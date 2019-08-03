package view.MenuAtendente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
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
		
		lstVoos = new JList<String>();
		lstVoos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(lstVoos);
		
		panel = new JPanel();
		panel.setBounds(12, 217, 416, 41);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 20, 0));
		
		btnCancelarVoo = new JButton("Cancelar Voo");
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
