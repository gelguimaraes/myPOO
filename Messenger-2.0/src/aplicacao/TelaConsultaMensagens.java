package aplicacao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import fachada.Fachada;
import modelo.Mensagem;


public class TelaConsultaMensagens extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField inputBusca;
	private JList<String> listMensagens;
	private DefaultListModel<String> model;
	private ArrayList<Mensagem> listMensagensAchadas;
	private JScrollPane scrollPane;
	private JLabel lblMensagens;
	private JLabel lblNoMensagem;
	private JLabel lblConsMensagens;
	private JLabel lblDigiteUmNome;
	private JButton btnFechar;
	private JButton btnVisualizar;
	private JButton btnBuscar;
	private TelaViewMensagem viewMsg;
	private String id;



	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultaMensagens window = new TelaConsultaMensagens();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaConsultaMensagens() {
		getContentPane().setBackground(new Color(0, 139, 139));
		getContentPane().setLayout(null);
		
		lblConsMensagens = new JLabel("Consultar Mensagens");
		lblConsMensagens.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsMensagens.setForeground(new Color(255, 255, 255));
		lblConsMensagens.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblConsMensagens.setBounds(10, 11, 364, 28);
		getContentPane().add(lblConsMensagens);
		
		lblDigiteUmNome = new JLabel("Digite uma palavra para buscar ");
		lblDigiteUmNome.setForeground(new Color(255, 255, 255));
		lblDigiteUmNome.setBounds(10, 38, 364, 28);
		getContentPane().add(lblDigiteUmNome);
		
		inputBusca = new JTextField();
		inputBusca.setBorder(new LineBorder(new Color(0, 128, 128), 2));
		inputBusca.setBounds(10, 63, 252, 28);
		getContentPane().add(inputBusca);
		inputBusca.setColumns(10);
		
		lblMensagens = new JLabel("");
		lblMensagens.setForeground(Color.YELLOW);
		lblMensagens.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 11));
		lblMensagens.setBounds(10, 102, 364, 28);
		getContentPane().add(lblMensagens);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 128, 128), 2));
		scrollPane.setBounds(10, 136, 364, 154);
		scrollPane.setVisible(false);
		getContentPane().add(scrollPane);
		

		model = new DefaultListModel<String>();
		listMensagens = new JList<String>(model);
		listMensagens.setForeground(new Color(0, 0, 128));
		listMensagens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listMensagens.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		listMensagens.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				String toString = model.get(listMensagens.getSelectedIndex());
				int ind;
				ind = toString.indexOf("º");
				lblNoMensagem.setText("Mensagem nº: " + toString.substring(ind + 2, ind + 5) + " selecionada");
				lblNoMensagem.setVisible(true);
				btnVisualizar.setVisible(true);
				}
			});
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String palavra = inputBusca.getText();
				String trecho, idFormat;
				int indfirst, indlast, rangeFirst, rangelast;
				model.clear();
				lblNoMensagem.setVisible(false);
				try {
					listMensagensAchadas = Fachada.consultarMensagens(palavra);
					for(Mensagem m : listMensagensAchadas) {
						indfirst = m.getTexto().indexOf(palavra);
						indlast  = m.getTexto().indexOf(palavra);
						if ((indfirst - 15) < 0) {
							rangeFirst = 0;
						}else 
							rangeFirst = 15;
						
						if((indlast + 15) > m.getTexto().length()) {
							rangelast = m.getTexto().length();
							indlast = 0;
						}else 
							rangelast = 15;
						
						trecho = "..." + m.getTexto().substring(indfirst - rangeFirst, indlast + rangelast) + "...";
						idFormat = String.format("%02d",  m.getId());
						model.addElement("Mensagem Nº " + idFormat + "  Trecho:  " + trecho );
					}
					if(listMensagensAchadas.size() > 0) {
						lblMensagens.setText("Foram encotrados " + listMensagensAchadas.size() + " registro(s)");
						scrollPane.setVisible(true);
						scrollPane.setViewportView(listMensagens);
						
					}else {
						lblMensagens.setText("Nenhuma mensagem foi encontrada com a palavra: " + palavra);
						scrollPane.setVisible(false);
					}
					
				} catch (Exception e) {
					lblMensagens.setText(e.getMessage());
				}
			}
		});
		btnBuscar.setBackground(new Color(255, 255, 255));
		btnBuscar.setBorder(new LineBorder(new Color(0, 128, 128), 2));
		btnBuscar.setForeground(new Color(0, 128, 128));
		btnBuscar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		btnBuscar.setBounds(274, 63, 100, 28);
		getContentPane().add(btnBuscar);
		

		btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnFechar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnFechar.setBackground(Color.YELLOW);
		btnFechar.setBounds(10, 326, 89, 40);
		getContentPane().add(btnFechar);
		
		
		btnVisualizar = new JButton("Visualizar");
		btnVisualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					int ind;
					ind = lblNoMensagem.getText().indexOf("º");
					id = lblNoMensagem.getText().substring(ind + 3 , ind + 5);
					//System.out.println(id);
					viewMsg = new TelaViewMensagem(Integer.parseInt(id), "");
					viewMsg.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					
				}
			}
		});
		btnVisualizar.setBackground(new Color(100, 149, 237));
		btnVisualizar.setForeground(Color.WHITE);
		btnVisualizar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnVisualizar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnVisualizar.setBounds(109, 326, 89, 40);
		btnVisualizar.setVisible(false);
		getContentPane().add(btnVisualizar);
		
		
		lblNoMensagem = new JLabel("Clique na mensagem para visualiz\u00E1-la");
		lblNoMensagem.setForeground(new Color(255, 255, 0));
		lblNoMensagem.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 13));
		lblNoMensagem.setBounds(10, 301, 357, 14);
		lblNoMensagem.setVisible(false);
		getContentPane().add(lblNoMensagem);
		
		
		setTitle("Consultar Mensagens");
		setBounds(100, 100, 400, 410);
		
	}
	
	public TelaViewMensagem getTelaViewMensagem() {
		return viewMsg;
	}
}
