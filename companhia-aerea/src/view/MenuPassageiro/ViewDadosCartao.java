package view.MenuPassageiro;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import pagamento.CartaoCreditoMock;
import principal.Sistema;
import usuario.Usuario;
import view.Comum.ViewProcurarVoo;
import view.MenuPrincipal.ViewMenuPrincipal;

public class ViewDadosCartao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	
	private JLabel lblNomeCartao;
	private JLabel lblNumCartao;
	private JLabel lblValidadeCartao;
	private JLabel lblCVV;
	
	private MaskFormatter validadeCartao;
	private MaskFormatter numCartao;
	private MaskFormatter cvv;
	
	private JTextField txtNomeCartao;
	private JTextField txtNumCartao;
	private JTextField txtValidadeCartao;
	private JTextField txtCVV;
	
	private JPanel panel_2;
	private JButton btnConfirmaCartao;
	private JButton btnSair;

	public ViewDadosCartao() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		
		setTitle("Informe os Dados do Cartão");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 446, 400);
		setLocationRelativeTo(null);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 0, 412, 0);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
	
		panel_1 = new JPanel();
		panel_1.setBounds(12, 35, 412, 200);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		//Número do cartão
		
		try {
			numCartao = new MaskFormatter("#### #### #### ####");
		} catch (ParseException e2) {
			Sistema.mostraAviso(e2.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
		
		numCartao.setValidCharacters("0123456789");
		numCartao.setAllowsInvalid(false);
		
		lblNumCartao = new JLabel("Número do Cartao:");
		lblNumCartao.setBounds(0, 0, 150, 25);
		panel_1.add(lblNumCartao);
		
		txtNumCartao = new JFormattedTextField(numCartao);
		txtNumCartao.setBounds(0, 25, 327, 25);
		panel_1.add(txtNumCartao);
		txtNumCartao.setColumns(12);
		
		//Nome
		
		lblNomeCartao = new JLabel("Nome:");
		lblNomeCartao.setBounds(0, 60, 100, 25);
		panel_1.add(lblNomeCartao);
		
		txtNomeCartao = new JTextField();
		txtNomeCartao.setBounds(0, 85, 327, 25);
		panel_1.add(txtNomeCartao);
		txtNomeCartao.setColumns(12);
		
		//Data de validade
		
		try {
			validadeCartao = new MaskFormatter("##/##");
		} catch (ParseException e2) {
			Sistema.mostraAviso(e2.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
		
		validadeCartao.setValidCharacters("0123456789");
		validadeCartao.setAllowsInvalid(false);
		
		txtValidadeCartao = new JFormattedTextField(validadeCartao);
		txtValidadeCartao.setHorizontalAlignment(SwingConstants.CENTER);
		txtValidadeCartao.setBounds(0, 145, 100, 25);
		panel_1.add(txtValidadeCartao);
		
		lblValidadeCartao = new JLabel("Data de Validade:");
		lblValidadeCartao.setBounds(0, 120, 150, 25);
		panel_1.add(lblValidadeCartao);
		
		txtValidadeCartao.setColumns(12);
		
		//CVV
		
		try {
			cvv = new MaskFormatter("###");
		} catch (ParseException e2) {
			Sistema.mostraAviso(e2.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
		
		cvv.setValidCharacters("0123456789");
		cvv.setAllowsInvalid(false);
				
		lblCVV = new JLabel("CVV:");
		lblCVV.setBounds(175, 120, 50, 25);
		panel_1.add(lblCVV);
		
		txtCVV = new JFormattedTextField(cvv);
		txtCVV.setHorizontalAlignment(SwingConstants.CENTER);
		txtCVV.setBounds(175, 145, 50, 25);
		panel_1.add(txtCVV);
		txtNomeCartao.setColumns(12);
			
		
		panel_2 = new JPanel();
		panel_2.setBounds(12, 250, 412, 100);
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 20));
		
		btnConfirmaCartao = new JButton("Confirmar");
		btnConfirmaCartao.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {

					try {
						
						String numCartao = txtNumCartao.getText();
						String nomeCartao = txtNomeCartao.getText();
						String dataValidade = txtValidadeCartao.getText();
						int cvv = Integer.parseInt(txtCVV.getText());
						
						Usuario.cobra(new CartaoCreditoMock(numCartao, nomeCartao, dataValidade, cvv));
						new ViewProcurarVoo().setVisible(true);
						dispose();
						
					} catch (Exception e) {
						Sistema.mostraAviso(e.getMessage(), JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		);
		
		panel_2.add(btnConfirmaCartao);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		panel_2.add(btnSair);
	}
	
	private void sair() {
		new ViewMenuPrincipal().setVisible(true);
		dispose();
	}

}
