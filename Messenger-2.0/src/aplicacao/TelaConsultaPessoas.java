package aplicacao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import fachada.Fachada;
import modelo.Pessoa;
import javax.swing.ListSelectionModel;

public class TelaConsultaPessoas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField inputBusca;
	private JLabel lblDigiteUmNome;
	private JButton btnBuscar;
	private JList<String> listPessoas;
	private DefaultListModel<String> model;
	private ArrayList<Pessoa> listPessoasAchadas;
	private JButton btnSelecionar;
	private JScrollPane scrollPane;
	private JLabel lblMensagens;
	private JLabel lblEncDest;
	private JButton btnFechar;
	private static JTextField inputDestinatario; 


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultaPessoas window = new TelaConsultaPessoas(inputDestinatario);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaConsultaPessoas(JTextField inputDestinatario) {
		getContentPane().setBackground(new Color(176, 224, 230));
		getContentPane().setLayout(null);
		
		lblEncDest = new JLabel("Encontrar Destinat\u00E1rio (Lista de Pessoas)");
		lblEncDest.setHorizontalAlignment(SwingConstants.CENTER);
		lblEncDest.setForeground(new Color(0, 0, 128));
		lblEncDest.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblEncDest.setBounds(10, 11, 364, 14);
		getContentPane().add(lblEncDest);
		
		lblDigiteUmNome = new JLabel("Digite um nome ou parte dele para buscar ");
		lblDigiteUmNome.setBounds(10, 38, 364, 28);
		getContentPane().add(lblDigiteUmNome);
		
		inputBusca = new JTextField();
		inputBusca.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		inputBusca.setBounds(10, 63, 252, 28);
		getContentPane().add(inputBusca);
		inputBusca.setColumns(10);
		
		lblMensagens = new JLabel("Caso queira listar todos deixe o campo em branco.");
		lblMensagens.setForeground(new Color(255, 0, 0));
		lblMensagens.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 11));
		lblMensagens.setBounds(10, 102, 364, 28);
		getContentPane().add(lblMensagens);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = inputBusca.getText();
				model.clear();
				
				try {
					listPessoasAchadas = Fachada.listarPessoas(nome);
					for(Pessoa p : listPessoasAchadas) {
						model.addElement(p.getNome() + " (" + p.getEmail() + ")");
					}
					if(listPessoasAchadas.size() > 0) {
						lblMensagens.setText("Foram encotrados " + listPessoasAchadas.size() + " registro(s)");
						scrollPane.setVisible(true);
						btnSelecionar.setVisible(true);
						
					}else {
						lblMensagens.setText("Nenhuma pessoa foi encontrada com este nome: " + nome);
						scrollPane.setVisible(false);
						btnSelecionar.setVisible(false);
					}
					
				} catch (Exception e) {
					lblMensagens.setText(e.getMessage());
				}
			}
		});
		btnBuscar.setBackground(new Color(0, 0, 128));
		btnBuscar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnBuscar.setForeground(new Color(255, 255, 255));
		btnBuscar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		btnBuscar.setBounds(274, 63, 100, 28);
		getContentPane().add(btnBuscar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		scrollPane.setBounds(10, 136, 364, 125);
		scrollPane.setVisible(false);
		getContentPane().add(scrollPane);
		

		model = new DefaultListModel<String>();
		listPessoas = new JList<String>(model);
		listPessoas.setForeground(new Color(0, 0, 128));
		listPessoas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPessoas.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		scrollPane.setViewportView(listPessoas);
	
		
		btnSelecionar = new JButton("Selecionar Destinat\u00E1rio");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String toString = model.get(listPessoas.getSelectedIndex());
		        int indFirst;
		        int indLast;
		        indFirst = toString.indexOf("(");
		        indLast = toString.lastIndexOf(")");
				inputDestinatario.setText(toString.substring(indFirst + 1, indLast));
				dispose();
			}
		});
		btnSelecionar.setBackground(new Color(0, 0, 128));
		btnSelecionar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnSelecionar.setForeground(new Color(255, 255, 255));
		btnSelecionar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		btnSelecionar.setBounds(160, 272, 214, 40);
		btnSelecionar.setVisible(false);
		getContentPane().add(btnSelecionar);
		
		btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnFechar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnFechar.setBackground(Color.YELLOW);
		btnFechar.setBounds(10, 272, 89, 40);
		getContentPane().add(btnFechar);
		setTitle("Consultar Pessoas");

		setBounds(100, 100, 400, 361);
		
	}
}
