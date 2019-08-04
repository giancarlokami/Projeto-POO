package view.Comum;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

import aviao.Poltrona;

import principal.Sistema;

import javax.swing.ScrollPaneConstants;
import java.awt.Font;

public class ViewSelecionarPoltronas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnConfirmarSelecao;
	private JButton btnSair;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private ArrayList<Poltrona> selecionadas = new ArrayList<Poltrona>();
	private JScrollPane scrollPane_1;
	private JTextArea txtrPoltronasSelecionadas;
	
	public ViewSelecionarPoltronas() {
		setResizable(false);
		setTitle("Selecionar Poltronas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 591);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(12, 515, 431, 34);
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 20, 0));
		
		btnConfirmarSelecao = new JButton("Confirmar Selecao");
		btnConfirmarSelecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (selecionadas != null && selecionadas.size() > 0) {
					try {
						Sistema.setPoltronasUsuario(selecionadas);
						new ViewReservarVoo().setVisible(true);
						dispose();
					} catch (Exception e) {
						Sistema.mostraAviso(e.getMessage(), JOptionPane.ERROR_MESSAGE);
					}
				} else {
					Sistema.mostraAviso("È preciso selecionar poltronas", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panel.add(btnConfirmarSelecao);
		
		btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sair();
			}
		});
		panel.add(btnSair);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 75, 431, 428);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new GridLayout(0, 7, 0, 0));
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(12, 12, 431, 51);
		contentPane.add(scrollPane_1);
		
		txtrPoltronasSelecionadas = new JTextArea();
		txtrPoltronasSelecionadas.setToolTipText("Poltronas Selecionadas");
		txtrPoltronasSelecionadas.setText("Poltronas Selecionadas:");
		txtrPoltronasSelecionadas.setLineWrap(true);
		txtrPoltronasSelecionadas.setFont(new Font("Dialog", Font.BOLD, 12));
		txtrPoltronasSelecionadas.setEditable(false);
		txtrPoltronasSelecionadas.setBackground(UIManager.getColor("Button.background"));
		scrollPane_1.setViewportView(txtrPoltronasSelecionadas);

		//Primeira Classe
		int aux = 0;
		for (int i = 0; i < 5; i++) {
			panel_1.add(new JLabel(""));
			panel_1.add(criaBtn(aux++));
			panel_1.add(criaBtn(aux++));
			panel_1.add(new JLabel(""));
			panel_1.add(criaBtn(aux++));
			panel_1.add(criaBtn(aux++));
			panel_1.add(new JLabel(""));
		}
		
		//Classe Economica
		for (int i = 0; i < 15; i++) {
			panel_1.add(criaBtn(aux++));
			panel_1.add(criaBtn(aux++));
			panel_1.add(criaBtn(aux++));
			panel_1.add(new JLabel(""));
			panel_1.add(criaBtn(aux++));
			panel_1.add(criaBtn(aux++));
			panel_1.add(criaBtn(aux++));
		}
	}
	
	
	private JButton criaBtn(int id) {
		ArrayList<Poltrona> poltronas = Sistema.getVooAtual().getPoltronas();
		JButton btn = new JButton(Integer.toString(poltronas.get(id).getNumero()));
		
		if (poltronas.get(id).estaOcupada()) {
			btn.setBackground(new Color(246, 39, 40));
		} else {
			btn.setBackground(new Color(101, 225, 40));
		}
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!poltronas.get(id).estaOcupada() && !selecionadas.contains(poltronas.get(id))) {
					selecionadas.add(poltronas.get(id));
					btn.setBackground(new Color(76, 154, 227));
					atualizaTxt();
				} else if (selecionadas.contains(poltronas.get(id))) {
					selecionadas.remove(poltronas.get(id));
					btn.setBackground(new Color(101, 225, 40));
					atualizaTxt();
				}
			}
		});
		return btn;
	}
	
	private void atualizaTxt() {
		String polt = "";
		Collections.sort(selecionadas, new Comparator<Poltrona>() {
			public int compare(Poltrona p1, Poltrona p2) {
				return p1.getNumero() - p2.getNumero();
			}
		});
		
		for (Poltrona p : selecionadas) {
			polt += " " + p.getNumero() + ";";
		}
		txtrPoltronasSelecionadas.setText("Poltronas Selecionadas:" + polt);
	}
	
	private void sair() {
		new ViewReservarVoo().setVisible(true);
		dispose();
	}
}
