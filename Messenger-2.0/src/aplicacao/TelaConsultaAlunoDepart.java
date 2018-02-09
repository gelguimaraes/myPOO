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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import fachada.Fachada;
import modelo.Aluno;
import modelo.Funcionario;
import modelo.Pessoa;

public class TelaConsultaAlunoDepart extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField inputBusca;
	private JLabel lblDigiteUmNome;
	private JButton btnBuscar;
	private JList<String> listTipos;
	private DefaultListModel<String> model;
	private ArrayList<Pessoa> listTiposAchados;
	private JScrollPane scrollPane;
	private JLabel lblMensagens;
	private JLabel lblEncDest;
	private JButton btnFechar;
	private static String tipo;
	private String tipoPessoa;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConsultaAlunoDepart window = new TelaConsultaAlunoDepart(tipo);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaConsultaAlunoDepart(String tipo) {
		getContentPane().setBackground(new Color(176, 224, 230));
		getContentPane().setLayout(null);
		
		if(tipo == "Curso") {
			tipoPessoa = "Alunos";
		}else {
			tipoPessoa = "Funcion\u00E1rios";
		}
		
		lblEncDest = new JLabel("Consultar "+ tipo +" (Lista de "+ tipoPessoa +")");
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
		
		lblMensagens = new JLabel("");
		lblMensagens.setForeground(new Color(255, 0, 0));
		lblMensagens.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 11));
		lblMensagens.setBounds(10, 102, 364, 28);
		getContentPane().add(lblMensagens);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nome = inputBusca.getText();
				model.clear();
				
				if(tipo.equals("Curso")) {
					try {
						listTiposAchados = Fachada.listarTipo(nome, "Curso");
						for(Pessoa p : listTiposAchados) {
							model.addElement(p.getNome() + " (" + ((Aluno) p).getCurso() + ")" );
						}
						if(listTiposAchados.size() > 0) {
							
							lblMensagens.setText("Encotrado(s) " + listTiposAchados.size() + " Aluno(s)");
							scrollPane.setVisible(true);
							
						}else {
							lblMensagens.setText("Nenhum aluno foi encontrado para este curso: " + nome);
							scrollPane.setVisible(false);
						}
						
					} catch (Exception e) {
						lblMensagens.setText(e.getMessage());
					}
				
				}else  {
					try {
						listTiposAchados = Fachada.listarTipo(nome, "Departamento");
						for(Pessoa p : listTiposAchados) {
							model.addElement(p.getNome() + " (" + ((Funcionario) p).getDepartamento() + ")" );
						}
						if(listTiposAchados.size() > 0) {
							lblMensagens.setText("Encotrado(s) " + listTiposAchados.size() + " Funcion\u00E1rio(s)");
							scrollPane.setVisible(true);
							
						}else {
							lblMensagens.setText("Nenhum funcion\u00E1rio encontrado para este departamento: " + nome);
							scrollPane.setVisible(false);
						}
						
					} catch (Exception e) {
						lblMensagens.setText(e.getMessage());
					}
				
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
		listTipos = new JList<String>(model);
		listTipos.setForeground(new Color(0, 0, 128));
		listTipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTipos.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		scrollPane.setViewportView(listTipos);
		
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
		setTitle("Consultar "+ tipo);

		setBounds(100, 100, 400, 361);
		
	}
}
