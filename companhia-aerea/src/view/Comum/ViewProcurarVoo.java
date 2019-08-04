package view.Comum;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import principal.Sistema;
import usuario.Atendente;
import view.MenuAtendente.ViewMenuAtendente;
import view.MenuPassageiro.ViewMenuPassageiro;

import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class ViewProcurarVoo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnSelecionarVoo;
	private JButton btnSair;
	private JPanel panel_1;
	private JLabel lblData;
	private JFormattedTextField txtData;
	private MaskFormatter fmtData;
	private JFormattedTextField txtHora;
	private MaskFormatter fmtHora;
	private JLabel lblHora;
	private JLabel lblOrigem;
	private JTextField txtOrigem;
	private JLabel lblDestino;
	private JTextField txtDestino;
	private JLabel lblVoos;
	private JScrollPane scrollPane;
	private JList<String> lstVoos;
	private JButton btnAplicarFiltro;
	private JPanel panel_2;
	private JButton btnLimparfiltro;

	public ViewProcurarVoo() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		setTitle("Procurar Voo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 474);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 382, 392, 50);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 2, 20, 0));
		
		btnSelecionarVoo = new JButton("Selecionar Voo");
		btnSelecionarVoo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Sistema.setVooAtual(Sistema.getVooFromString(lstVoos.getSelectedValue()));
					new ViewReservarVoo().setVisible(true);
					dispose();
				} catch (Exception e) {
					Sistema.mostraAviso(e.getMessage(), JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnSelecionarVoo);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		panel.add(btnSair);
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 12, 394, 358);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		lblData = new JLabel("Data:");
		lblData.setHorizontalAlignment(SwingConstants.LEFT);
		lblData.setBounds(12, 13, 47, 23);
		panel_1.add(lblData);
		
		try {
			fmtData = new MaskFormatter("##/##/####");
		} catch (ParseException e2) {
			Sistema.mostraAviso(e2.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
		
		fmtData.setValidCharacters("0123456789");
		fmtData.setAllowsInvalid(false);
		
		txtData = new JFormattedTextField(fmtData);
		txtData.setToolTipText("Data do Voo");
		txtData.setHorizontalAlignment(SwingConstants.CENTER);
		txtData.setBounds(88, 12, 85, 25);
		panel_1.add(txtData);
		
		try {
			fmtHora = new MaskFormatter("##:##");
		} catch (ParseException e1) {
			Sistema.mostraAviso(e1.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
		
		fmtHora.setValidCharacters("0123456789");
		fmtHora.setAllowsInvalid(false);
		
		txtHora = new JFormattedTextField(fmtHora);
		txtHora.setToolTipText("Hora do Voo");
		txtHora.setHorizontalAlignment(SwingConstants.CENTER);
		txtHora.setBounds(313, 12, 60, 25);
		panel_1.add(txtHora);
		
		lblHora = new JLabel("Hora:");
		lblHora.setHorizontalAlignment(SwingConstants.LEFT);
		lblHora.setBounds(254, 13, 47, 23);
		panel_1.add(lblHora);
		
		lblOrigem = new JLabel("Origem:");
		lblOrigem.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrigem.setBounds(12, 50, 68, 23);
		panel_1.add(lblOrigem);
		
		txtOrigem = new JTextField();
		txtOrigem.setToolTipText("Origem do Voo");
		txtOrigem.setHorizontalAlignment(SwingConstants.LEFT);
		txtOrigem.setColumns(8);
		txtOrigem.setBounds(88, 49, 285, 25);
		panel_1.add(txtOrigem);
		
		lblDestino = new JLabel("Destino:");
		lblDestino.setHorizontalAlignment(SwingConstants.LEFT);
		lblDestino.setBounds(12, 88, 68, 23);
		panel_1.add(lblDestino);
		
		txtDestino = new JTextField();
		txtDestino.setToolTipText("Destino do Voo");
		txtDestino.setHorizontalAlignment(SwingConstants.LEFT);
		txtDestino.setColumns(8);
		txtDestino.setBounds(88, 90, 285, 25);
		panel_1.add(txtDestino);
		
		lblVoos = new JLabel("Voos:");
		lblVoos.setBounds(12, 161, 66, 15);
		panel_1.add(lblVoos);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 188, 361, 158);
		panel_1.add(scrollPane);
		
		DefaultListModel<String> model = new DefaultListModel<String>();
		model.addAll(Sistema.getVoosAtuais().stream().map(v -> v.toString()).collect(Collectors.toList()));
		lstVoos = new JList<String>();
		lstVoos.setModel(model);
		scrollPane.setViewportView(lstVoos);
		
		panel_2 = new JPanel();
		panel_2.setBounds(12, 123, 370, 33);
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 20, 0));
		
		btnAplicarFiltro = new JButton("Aplicar Filtro");
		panel_2.add(btnAplicarFiltro);
		
		btnLimparfiltro = new JButton("Limpar Filtro");
		btnLimparfiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtData.setText("");
				txtHora.setText("");
				txtOrigem.setText("");
				txtDestino.setText("");
				String[] filtro = new String[]
						{txtData.getText(), txtHora.getText(), txtOrigem.getText(), txtDestino.getText()};
				atualizaFiltro(filtro);
			}
		});
		panel_2.add(btnLimparfiltro);
		btnAplicarFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] filtro = new String[]
				{txtData.getText(), txtHora.getText(), txtOrigem.getText(), txtDestino.getText()};
				atualizaFiltro(filtro);
			}
		});
	}
	private void sair() {
		if (Sistema.getUsuarioAtual() instanceof Atendente) {
			new ViewMenuAtendente().setVisible(true);
		} else {
			new ViewMenuPassageiro().setVisible(true);
		}
		dispose();
	}
	
	private void atualizaFiltro(String[] filtro) {
		try {
			DefaultListModel<String> model = new DefaultListModel<String>();
			if (Sistema.getQtdVoos() > 0) {
				model.addAll(Sistema.getVoosFiltrados(filtro).stream().map(v -> v.toString()).collect(Collectors.toList()));
				lstVoos.setModel(model);
			}
			Sistema.mostraAviso("Filtro aplicado!", JOptionPane.PLAIN_MESSAGE);
		} catch (Exception e) {
			Sistema.mostraAviso(e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}
}
