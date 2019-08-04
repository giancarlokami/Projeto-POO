package view.MenuAtendente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import aviao.Aviao;
import aviao.Voo;
import principal.Sistema;

import javax.swing.JLabel;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewCriarVoo extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JPanel panel_1;
	private JButton button;
	private JButton button_1;
	private JLabel lblData;
	private JFormattedTextField txtData;
	private MaskFormatter fmtData;
	private JLabel lblHora;
	private JFormattedTextField txtHora;
	private MaskFormatter fmtHora;
	private JLabel lblOrigem;
	private JTextField txtOrigem;
	private JLabel lblDestino;
	private JTextField txtDestino;
	private JLabel lblAviao;
	private JScrollPane scrollPane;
	private DefaultListModel<String> model;
	private JList<String> lstAvioes;
	private JButton btnVerificarDisponibilidade;
	private JLabel lblValorClasseEconomica;
	private JFormattedTextField txtValorClasseEconomica;
	private JLabel lblValorPrimeiraClasse;
	private JFormattedTextField txtValorPrimeiraClasse;

	public ViewCriarVoo() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				sair();
			}
		});
		
		setTitle("Criar Voo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 409, 451);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 12, 385, 346);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblData = new JLabel("Data:");
		lblData.setHorizontalAlignment(SwingConstants.LEFT);
		lblData.setBounds(12, 14, 47, 23);
		panel.add(lblData);
		
		try {
			fmtData = new MaskFormatter("##/##/####");
		} catch (ParseException e2) {
			Sistema.mostraAviso(e2.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
		
		fmtData.setValidCharacters("0123456789");
		txtData = new JFormattedTextField(fmtData);
		txtData.setToolTipText("Data do Voo");
		txtData.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		txtData.setHorizontalAlignment(SwingConstants.CENTER);
		txtData.setBounds(88, 13, 85, 25);
		panel.add(txtData);
		
		lblHora = new JLabel("Hora:");
		lblHora.setHorizontalAlignment(SwingConstants.LEFT);
		lblHora.setBounds(254, 14, 47, 23);
		panel.add(lblHora);
		
		try {
			fmtHora = new MaskFormatter("##:##");
		} catch (ParseException e1) {
			Sistema.mostraAviso(e1.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
		
		fmtHora.setValidCharacters("0123456789");
		txtHora = new JFormattedTextField(fmtHora);
		txtHora.setToolTipText("Hora do Voo");
		txtHora.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
		txtHora.setHorizontalAlignment(SwingConstants.CENTER);
		txtHora.setBounds(313, 13, 60, 25);
		panel.add(txtHora);
		
		lblOrigem = new JLabel("Origem:");
		lblOrigem.setHorizontalAlignment(SwingConstants.LEFT);
		lblOrigem.setBounds(12, 51, 68, 23);
		panel.add(lblOrigem);
		
		txtOrigem = new JTextField();
		txtOrigem.setToolTipText("Origem do Voo");
		txtOrigem.setHorizontalAlignment(SwingConstants.LEFT);
		txtOrigem.setColumns(8);
		txtOrigem.setBounds(88, 50, 285, 25);
		panel.add(txtOrigem);
		
		lblDestino = new JLabel("Destino:");
		lblDestino.setHorizontalAlignment(SwingConstants.LEFT);
		lblDestino.setBounds(12, 89, 68, 23);
		panel.add(lblDestino);
		
		txtDestino = new JTextField();
		txtDestino.setToolTipText("Destino do Voo");
		txtDestino.setHorizontalAlignment(SwingConstants.LEFT);
		txtDestino.setColumns(8);
		txtDestino.setBounds(88, 91, 285, 25);
		panel.add(txtDestino);
		
		lblAviao = new JLabel("Aviao:");
		lblAviao.setEnabled(false);
		lblAviao.setBounds(12, 213, 66, 15);
		panel.add(lblAviao);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 211, 285, 88);
		panel.add(scrollPane);

		lstAvioes = new JList<String>();
		lstAvioes.setToolTipText("Lista de Avioes Disponiveis");
		lstAvioes.setEnabled(false);
		lstAvioes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(lstAvioes);
		
		btnVerificarDisponibilidade = new JButton("Verificar Disponibilidade");
		btnVerificarDisponibilidade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					model = new DefaultListModel<String>();
					LocalDate data = LocalDate.parse(txtData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					LocalTime hora = LocalTime.parse(txtHora.getText(), DateTimeFormatter.ofPattern("HH:mm"));
					lblAviao.setEnabled(true);
					lstAvioes.setEnabled(true);
					if (Sistema.getQtdAvioes() > 0) {
						model.addAll(Sistema.getAvioesDisponiveis(data, hora).stream().map(a -> a.getNome()).collect(Collectors.toList()));
					}
					lstAvioes.setModel(model);
				} catch (Exception e) {
					lblAviao.setEnabled(false);
					lstAvioes.setEnabled(false);
				}
			}
		});
		btnVerificarDisponibilidade.setBounds(88, 311, 285, 25);
		panel.add(btnVerificarDisponibilidade);
		
		lblValorClasseEconomica = new JLabel("Valor Classe Economica:");
		lblValorClasseEconomica.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorClasseEconomica.setBounds(12, 171, 176, 23);
		panel.add(lblValorClasseEconomica);
		
		txtValorClasseEconomica = new JFormattedTextField();
		txtValorClasseEconomica.setToolTipText("Valor da Passagem da Classe Economica");
		txtValorClasseEconomica.setHorizontalAlignment(SwingConstants.LEFT);
		txtValorClasseEconomica.setColumns(8);
		txtValorClasseEconomica.setBounds(190, 170, 183, 25);
		panel.add(txtValorClasseEconomica);
		
		lblValorPrimeiraClasse = new JLabel("Valor Primeira Classe:");
		lblValorPrimeiraClasse.setHorizontalAlignment(SwingConstants.LEFT);
		lblValorPrimeiraClasse.setBounds(12, 131, 176, 23);
		panel.add(lblValorPrimeiraClasse);
		
		txtValorPrimeiraClasse = new JFormattedTextField();
		txtValorPrimeiraClasse.setToolTipText("Valor da Passagem da Primeira Classe");
		txtValorPrimeiraClasse.setHorizontalAlignment(SwingConstants.LEFT);
		txtValorPrimeiraClasse.setColumns(8);
		txtValorPrimeiraClasse.setBounds(190, 130, 183, 25);
		panel.add(txtValorPrimeiraClasse);
		
		DecimalFormat dFormat = new DecimalFormat("#,###,###.00");
		NumberFormatter formatter = new NumberFormatter(dFormat);
		formatter.setFormat(dFormat);
		formatter.setAllowsInvalid(false);
		
		txtValorClasseEconomica.setFormatterFactory(new DefaultFormatterFactory (formatter));
		txtValorPrimeiraClasse.setFormatterFactory(new DefaultFormatterFactory (formatter));
		
		panel_1 = new JPanel();
		panel_1.setBounds(12, 372, 385, 37);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 20, 0));
		
		button = new JButton("Criar Voo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (lstAvioes.isEnabled()) {
					try {
						String origem = txtOrigem.getText();
						String destino = txtDestino.getText();
						LocalDate data = LocalDate.parse(txtData.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
						LocalTime hora = LocalTime.parse(txtHora.getText(), DateTimeFormatter.ofPattern("HH:mm"));
						double precoEconomica = Double.parseDouble(formatter.getFormat().parseObject(txtValorClasseEconomica.getText()).toString());
						double precoPrimeira = Double.parseDouble(formatter.getFormat().parseObject(txtValorPrimeiraClasse.getText()).toString());
						Aviao aviao = new Aviao(lstAvioes.getSelectedValue());
						Voo voo = Sistema.validaCriacaoVoo(origem, destino, data, hora, precoEconomica, precoPrimeira, aviao);
						Sistema.criaVoo(voo, aviao);
						Sistema.mostraAviso("Voo criado com sucesso!", JOptionPane.PLAIN_MESSAGE);
						model.remove(lstAvioes.getSelectedIndex());
					} catch (Exception e) {
						Sistema.mostraAviso(e.getMessage(), JOptionPane.ERROR_MESSAGE);
					}
				} else {
					Sistema.mostraAviso("È preciso verificar a disponibilidade dos aviões antes de criar o voo", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel_1.add(button);
		
		button_1 = new JButton("Sair");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		panel_1.add(button_1);
	}
	
	private void sair() {
		new ViewMenuAtendente().setVisible(true);
		dispose();
	}
}

