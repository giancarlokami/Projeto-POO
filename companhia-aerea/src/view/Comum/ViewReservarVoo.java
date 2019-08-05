package view.Comum;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import pagamento.BoletoBancarioMock;
import pagamento.CartaoCreditoMock;
import principal.Sistema;
import usuario.Usuario;
import view.MenuPassageiro.ViewDadosCartao;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.Font;

public class ViewReservarVoo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel01;
	private JPanel rdbtnPanel;
	private JRadioButton rdbtnCartao;
	private JRadioButton rdbtnBoleto;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnSair;
	private JButton btnReservarPoltronas;
	private JLabel lblInformaesDoVoo;
	private JLabel lblPagamento;
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
		setBounds(100, 100, 461, 425);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 325, 426, 45);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 20, 0));
		
		panel01 = new JPanel();
		panel01.setBounds(12, 250, 426, 35);
		contentPane.add(panel01);
		panel01.setLayout(new GridLayout(0, 2, 20, 0));
		
		rdbtnPanel = new JPanel();
		rdbtnPanel.setBounds(12, 285, 426, 25);
		contentPane.add(rdbtnPanel);
		rdbtnPanel.setLayout(new GridLayout(0, 2, 20, 0));
		
		//Opções de pagamento
		
		lblPagamento = new JLabel("Selecione a forma de pagamento:");
		lblPagamento.setBounds(12, 172, 195, 15);
		panel01.add(lblPagamento);
		
		rdbtnCartao = new JRadioButton("Cartão de Crédito");
		rdbtnCartao.setSelected(true);
		rdbtnCartao.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonGroup.add(rdbtnCartao);
		rdbtnCartao.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnPanel.add(rdbtnCartao);
		
		rdbtnBoleto = new JRadioButton("Boleto Bancário");
		
		rdbtnBoleto.setVerticalAlignment(SwingConstants.BOTTOM);
		buttonGroup.add(rdbtnBoleto);
		rdbtnBoleto.setHorizontalAlignment(SwingConstants.LEFT);
		rdbtnPanel.add(rdbtnBoleto);
		
		
		btnReservarPoltronas = new JButton("Reservar Poltronas");
		btnReservarPoltronas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Sistema.getPoltronasUsuario() != null && Sistema.getPoltronasUsuario().size() > 0) {
					try {
						if(rdbtnCartao.isSelected()) {
							
							new ViewDadosCartao().setVisible(true);
							dispose();
							
						}else if(rdbtnBoleto.isSelected()) {
							
							Usuario.cobra(new BoletoBancarioMock());
							new ViewProcurarVoo().setVisible(true);
							dispose();
							
						}
						
						//Sistema.mostraAviso("Poltronas reservadas com sucesso!", JOptionPane.PLAIN_MESSAGE);
					} catch (Exception e) {
						Sistema.mostraAviso(e.toString(), JOptionPane.ERROR_MESSAGE);
					}
				} else {
					Sistema.mostraAviso("É preciso selecionar poltronas", JOptionPane.ERROR_MESSAGE);
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
		txtrInformacoesDoVoo.setBounds(12, 30, 416, 93);
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
