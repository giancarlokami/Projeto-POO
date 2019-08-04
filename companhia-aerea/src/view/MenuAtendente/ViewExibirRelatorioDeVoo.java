package view.MenuAtendente;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Sistema;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewExibirRelatorioDeVoo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextPane txtpnRelatoriodevoo;
	private JButton btnSair;

	public ViewExibirRelatorioDeVoo() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		setTitle("Relatorio de Voo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 416, 201);
		contentPane.add(scrollPane);
		
		txtpnRelatoriodevoo = new JTextPane();
		txtpnRelatoriodevoo.setToolTipText("Relatorio de Voo");
		txtpnRelatoriodevoo.setText("Relatorio de Voo");
		txtpnRelatoriodevoo.setEditable(false);
		scrollPane.setViewportView(txtpnRelatoriodevoo);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		btnSair.setBounds(12, 225, 416, 33);
		contentPane.add(btnSair);
		
		String relatorio = Sistema.geraRelatorioDeVoo(Sistema.getVooAtual());
		txtpnRelatoriodevoo.setText(relatorio);
		
		try {
			
		} catch (Exception e) {
			Sistema.mostraAviso(e.toString(), JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void sair() {
		new ViewGerarRelatorioDeVoo().setVisible(true);
		dispose();
	}
}
