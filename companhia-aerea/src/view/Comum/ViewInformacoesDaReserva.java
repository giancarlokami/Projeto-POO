package view.Comum;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import principal.Sistema;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewInformacoesDaReserva extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea txtrInformacoesDaReserva;
	private JLabel lblInformacoesDaReserva;
	private JButton btnSair;

	public ViewInformacoesDaReserva() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		setResizable(false);
		setTitle("Informacoes da Reserva");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtrInformacoesDaReserva = new JTextArea();
		txtrInformacoesDaReserva.setToolTipText("Informacoes da Reserva");
		txtrInformacoesDaReserva.setLineWrap(true);
		txtrInformacoesDaReserva.setFont(new Font("Dialog", Font.BOLD, 12));
		txtrInformacoesDaReserva.setEditable(false);
		txtrInformacoesDaReserva.setBackground(UIManager.getColor("Button.background"));
		txtrInformacoesDaReserva.setBounds(22, 32, 406, 175);
		contentPane.add(txtrInformacoesDaReserva);
		
		if (Sistema.getVooAtual() != null) {
			txtrInformacoesDaReserva.setText(Sistema.getInfoReserva());
		}
		
		lblInformacoesDaReserva = new JLabel("Informacoes da Reserva");
		lblInformacoesDaReserva.setBounds(12, 12, 195, 15);
		contentPane.add(lblInformacoesDaReserva);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		btnSair.setBounds(12, 227, 416, 31);
		contentPane.add(btnSair);
	}
	
	private void sair() {
		new ViewConsultarReservas().setVisible(true);
		dispose();
	}
}
