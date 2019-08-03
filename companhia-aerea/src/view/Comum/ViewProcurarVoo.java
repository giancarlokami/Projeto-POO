package view.Comum;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Sistema;
import usuario.Atendente;
import view.MenuAtendente.ViewMenuAtendente;
import view.MenuPassageiro.ViewMenuPassageiro;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ViewProcurarVoo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnSelecionarVoo;
	private JButton btnSair;

	public ViewProcurarVoo() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		setTitle("Procurar Voo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 208, 426, 50);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 20, 0));
		
		btnSelecionarVoo = new JButton("Selecionar Voo");
		panel.add(btnSelecionarVoo);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		panel.add(btnSair);
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
