package view.Comum;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Sistema;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import java.awt.Font;

public class ViewReservarVoo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnSair;
	private JButton btnReservarPoltronas;
	private JLabel lblInformaesDoVoo;
	private JButton btnSelecionarPoltronas;
	private JLabel lblInformaesDaReserva;
	private JTextArea txtrInformacoesDaReserva;
	private JTextArea txtrInformacoesDoVoo;

	public ViewReservarVoo() {
		setResizable(false);
		setTitle("Reservar Voo");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 461, 383);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 292, 426, 49);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 20, 0));
		
		btnReservarPoltronas = new JButton("Reservar Poltronas");
		btnReservarPoltronas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Sistema.getPoltronasUsuario() != null && Sistema.getPoltronasUsuario().size() > 0) {
					try {
						Sistema.mostraAviso("Poltronas reservadas com sucesso!", JOptionPane.PLAIN_MESSAGE);
					} catch (Exception e) {
						Sistema.mostraAviso(e.toString(), JOptionPane.ERROR_MESSAGE);
					}
				} else {
					Sistema.mostraAviso("È preciso selecionar poltronas", JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		panel.add(btnReservarPoltronas);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		panel.add(btnSair);
		
		lblInformaesDoVoo = new JLabel("Informacoes do Voo:");
		lblInformaesDoVoo.setBounds(12, 12, 154, 15);
		contentPane.add(lblInformaesDoVoo);
		
		txtrInformacoesDoVoo = new JTextArea();
		txtrInformacoesDoVoo.setFont(new Font("Dialog", Font.BOLD, 12));
		txtrInformacoesDoVoo.setBounds(22, 30, 416, 93);
		contentPane.add(txtrInformacoesDoVoo);
		txtrInformacoesDoVoo.setBackground(UIManager.getColor("Button.background"));
		txtrInformacoesDoVoo.setEditable(false);
		txtrInformacoesDoVoo.setToolTipText("Informacoes do Voo");
		txtrInformacoesDoVoo.setWrapStyleWord(true);
		
		btnSelecionarPoltronas = new JButton("Selecionar Poltronas");
		btnSelecionarPoltronas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ViewSelecionarPoltronas().setVisible(true);
				dispose();
			}
		});
		btnSelecionarPoltronas.setBounds(12, 135, 426, 25);
		contentPane.add(btnSelecionarPoltronas);
		
		lblInformaesDaReserva = new JLabel("Informacoes da Reserva");
		lblInformaesDaReserva.setBounds(22, 172, 195, 15);
		contentPane.add(lblInformaesDaReserva);
		
		txtrInformacoesDaReserva = new JTextArea();
		txtrInformacoesDaReserva.setFont(new Font("Dialog", Font.BOLD, 12));
		txtrInformacoesDaReserva.setBounds(22, 187, 416, 93);
		txtrInformacoesDaReserva.setLineWrap(true);
		
		if (Sistema.getVooAtual() != null) {
			txtrInformacoesDoVoo.setText(Sistema.getInfoVoo(Sistema.getVooAtual()));
			if (Sistema.getPoltronasUsuario() != null && Sistema.getVooAtual().getPoltronas().containsAll(Sistema.getPoltronasUsuario())) {
				txtrInformacoesDaReserva.setText(Sistema.getInfoReserva());
			}
		}
		
		contentPane.add(txtrInformacoesDaReserva);
		txtrInformacoesDaReserva.setBackground(UIManager.getColor("Button.background"));
		txtrInformacoesDaReserva.setEditable(false);
		txtrInformacoesDaReserva.setToolTipText("Informacoes da Reserva");
		txtrInformacoesDaReserva.setLineWrap(true);
	}
	
	private void sair() {
		new ViewProcurarVoo().setVisible(true);
		dispose();
	}
}
