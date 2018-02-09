package aplicacao;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import fachada.Fachada;
import modelo.Administrador;
import modelo.Pessoa;


public class TelaPrincipal {
	private JFrame frmPrincipal;
	
	private JPanel painelLogin;
	
	private JTextField lblSeuEmail;
	private JTextField lblSuaSenha;
	private JTextField inputEmail;
	
	private JPasswordField inputSenha;
	private JLabel lblMensagensLogin;
	private JPanel painelBoasVindas;
	private JLabel lblNomeLogada;
	private JLabel lblCxEntrada;
	private JLabel lblCxSaida;
	private JLabel lblBemVindoMessenger;
	
	private JButton btnCxEntrada;
	private JButton btnCxSaida;
	private JButton btnEscreverMensagem;
	private JButton btnSair;
	private JButton btnEntrar;
	
	private String nomeLogada;
	private String emailLogada;
	private Pessoa logada;
	
	private TelaCaixaEntrada telacxEntrada;
	private TelaCaixaSaida telacxSaida;
	private TelaCadastro telacadastroaluno;
	private TelaCadastro telacadastrofuncionario;
	private TelaCreateMensagem telacreateMsg;
	private TelaListaTodasPessoas telalistatodaspessoas;
	private TelaListaTodasMensagens telalistatodasmensagens;
	private TelaConsultaAlunoDepart telaconsultaAluno;
	private TelaConsultaAlunoDepart telaconsultaDepart;
	private TelaConsultaMensagens telaconsultamensagens;
	private TelaListaEstatisticas telaestatisticas;
	
	private int nCxEntrada;
	private int nCxSaida;
	private JMenuBar menuBar;
	private JMenu mnConsultas;
	private JMenu mnListas;
	private JMenu mnCadastro;
	private JMenuItem mntmMensagens;
	private JMenuItem mntmCursos;
	private JMenuItem mntmDepartamentos;
	private JMenuItem mntmLMensagens;
	private JMenuItem mntmLPessoas;
	private JMenuItem mntmAluno;
	private JMenuItem mntmFuncionario;
	private JMenu mnEstatsticas;
	private JMenuItem mntmVisualizarEstatisticas;
	

	
	


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaPrincipal() {
		initialize();
	}


	private void initialize() {
		frmPrincipal = new JFrame()	;
		frmPrincipal.getContentPane().setBackground(new Color(176, 224, 230));
		frmPrincipal.getContentPane().setLayout(null);
		frmPrincipal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
					
					Fachada.cadastrarAdm("Admin","admin", "007", "DTI");
					
					Fachada.login("admin@messenger.com", "007");
					Fachada.cadastrarPessoa("João Firmino","joao", "123", "Funcionário", "DTI");
					Fachada.cadastrarPessoa("Maria Alice","maria", "abc", "Aluno", "Sistemas para Internet");
					Fachada.cadastrarPessoa("Maria Valentina","valentina", "abc", "Aluno", "Sistemas para Internet");
					Fachada.cadastrarPessoa("José Alves","jose", "456", "Aluno", "Direito");
					Fachada.cadastrarPessoa("Marlene","marlene", "123", "Aluno", "Marketing");
					Fachada.cadastrarPessoa("José Luiz","jl", "def","Funcionário","DCET");
					Fachada.cadastrarPessoa("Ronaldo","ro", "ro", "Funcionário", "DTI");
					Fachada.logoff();
					
					Fachada.login("joao@messenger.com", "123");
					Fachada.enviarMensagem(Fachada.getEmailDestinatario("maria@messenger.com"), "Você não faz ideia de como eu anotei seu nome no meu celular para não me esquecer de você.");
					Fachada.logoff();
					Fachada.login("maria@messenger.com", "abc");
					Fachada.enviarMensagem(Fachada.getEmailDestinatario("jose@messenger.com"), "Você é sempre tão simpática com estranhos?");
					Fachada.enviarMensagem(Fachada.getEmailDestinatario("maria@messenger.com"), "Mensagem própria");
					Fachada.logoff();
					Fachada.login("jose@messenger.com", "456");
					Fachada.enviarMensagem(Fachada.getEmailDestinatario("joao@messenger.com"), "Estou pensando em te dar a honra de ser minha nova parceira de msg de texto. Parabéns!");
					Fachada.logoff();
					Fachada.login("joao@messenger.com", "123");
					Fachada.enviarMensagem(Fachada.getEmailDestinatario("jose@messenger.com"), "Além de pensar em mim, o que está fazendo de bom?");
					Fachada.logoff();
					Fachada.login("maria@messenger.com", "abc");
					Fachada.enviarMensagem(Fachada.getEmailDestinatario("joao@messenger.com"), "Eu ouvi dizer que aliens estão sequestrando todas as pessoas sexys do mundo. Só queria me despedir...");
					Fachada.logoff();
					Fachada.login("jose@messenger.com", "456");
					Fachada.enviarMensagem(Fachada.getEmailDestinatario("maria@messenger.com"), "Jogar paciência ou sair com você? Decisão difícil...");
					Fachada.logoff();
					Fachada.login("joao@messenger.com", "123");
					Fachada.enviarMensagem(Fachada.getEmailDestinatario("joao@messenger.com"), "Eu só queria fazer você pegar o telefone. Pode voltar ao que estava fazendo.");
					Fachada.logoff();	
										
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
			
		});
		frmPrincipal.setTitle("Messenger");
		frmPrincipal.setBounds(100, 100, 413, 406);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		painelBoasVindas = new JPanel();
		painelBoasVindas.setBackground(new Color(176, 224, 230));
		painelBoasVindas.setBounds(0, 30, 403, 346);
		painelBoasVindas.setLayout(null);
		painelBoasVindas.setVisible(false);
		frmPrincipal.getContentPane().add(painelBoasVindas);
		
		lblNomeLogada = new JLabel("");
		lblNomeLogada.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeLogada.setForeground(new Color(0, 0, 128));
		lblNomeLogada.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		lblNomeLogada.setBounds(0, 11, 403, 25);
		painelBoasVindas.add(lblNomeLogada);
		
	
		lblCxEntrada = new JLabel("");
		lblCxEntrada.setHorizontalAlignment(SwingConstants.CENTER);
		lblCxEntrada.setBounds(0, 48, 403, 14);
		painelBoasVindas.add(lblCxEntrada);
		
		lblCxSaida = new JLabel("");
		lblCxSaida.setHorizontalAlignment(SwingConstants.CENTER);
		lblCxSaida.setBounds(0, 118, 403, 14);
		painelBoasVindas.add(lblCxSaida);
		
		btnCxEntrada = new JButton("Visualizar Caixa de Entrada");
		btnCxEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					telacxEntrada = new TelaCaixaEntrada(lblCxEntrada, lblCxSaida);
					telacxEntrada.setVisible(true);
				} catch (Exception e1) {				
					e1.printStackTrace();
					
				}
			}
				
		});
		btnCxEntrada.setForeground(new Color(255, 255, 255));
		btnCxEntrada.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnCxEntrada.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnCxEntrada.setBackground(new Color(123, 104, 238));
		btnCxEntrada.setBounds(42, 73, 316, 34);
		painelBoasVindas.add(btnCxEntrada);
		
		btnCxSaida = new JButton("Visualizar Caixa de Sa\u00EDda");
		btnCxSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					telacxSaida = new TelaCaixaSaida(lblCxEntrada, lblCxSaida);
					telacxSaida.setVisible(true);
				} catch (Exception e1) {				
					e1.printStackTrace();
					
				}
			}
		});
		btnCxSaida.setForeground(new Color(255, 255, 255));
		btnCxSaida.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnCxSaida.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnCxSaida.setBackground(new Color(219, 112, 147));
		btnCxSaida.setBounds(42, 143, 316, 34);
		painelBoasVindas.add(btnCxSaida);
		
		btnEscreverMensagem = new JButton("Escrever Nova Mensagem");
		btnEscreverMensagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					telacreateMsg = new TelaCreateMensagem(lblCxEntrada, lblCxSaida);
					telacreateMsg.setVisible(true);
				} catch (Exception e1) {				
					e1.printStackTrace();
					
				}
			}
		});
		btnEscreverMensagem.setForeground(new Color(255, 255, 255));
		btnEscreverMensagem.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		btnEscreverMensagem.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnEscreverMensagem.setBackground(new Color(0, 0, 128));
		btnEscreverMensagem.setBounds(42, 200, 316, 42);
		painelBoasVindas.add(btnEscreverMensagem);
		
		btnSair = new JButton("Sair");
		btnSair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Fachada.logoff();
					//limpando todos os inputs e labels
					inputEmail.setText("");
					inputSenha.setText("");
					lblMensagensLogin.setText("");
					
					painelBoasVindas.setVisible(false);
					painelLogin.setVisible(true);
					menuBar.setVisible(false);
					
					//fechando todas  as telas
					if(telacxEntrada != null) {
						telacxEntrada.dispose();
						if(telacxEntrada.getTelaViewMensagem() != null) 
							telacxEntrada.getTelaViewMensagem().dispose();
					
					}
					if(telacxSaida != null) {
						telacxSaida.dispose();
						if(telacxSaida.getTelaViewMensagem() != null) 
							telacxSaida.getTelaViewMensagem().dispose();
					}
					
					if(telacreateMsg != null) {
						telacreateMsg.dispose();
						if(telacreateMsg.getTelalistaPessoas() != null) 
							telacreateMsg.getTelalistaPessoas().dispose();
					}
					
					if(telalistatodasmensagens != null) {
						telalistatodasmensagens.dispose();
						if(telalistatodasmensagens.getTelaViewMensagem() != null) 
							telalistatodasmensagens.getTelaViewMensagem().dispose();
					}
					
					if(telacadastroaluno != null) {
						telacadastroaluno.dispose();
						
					}
					
					if(telacadastrofuncionario != null) {
						telacadastrofuncionario.dispose();
					}
					if(telalistatodaspessoas != null) 
						telalistatodaspessoas.dispose();

					if(telaconsultaAluno != null) 
						telaconsultaAluno.dispose();
					
					if(telaconsultaDepart != null) 
						telaconsultaDepart.dispose();

					if(telaconsultamensagens != null) 
						telaconsultamensagens.dispose();

					if(telaestatisticas != null) 
						telaestatisticas.dispose();

					
					JOptionPane.showMessageDialog(null,"Logoff realizado com sucesso!");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}

			}
		});
		btnSair.setForeground(new Color(255, 255, 255));
		btnSair.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnSair.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnSair.setBackground(new Color(165, 42, 42));
		btnSair.setBounds(326, 291, 51, 23);
		painelBoasVindas.add(btnSair);
		
		painelLogin = new JPanel();
		painelLogin.setBounds(42, 52, 316, 262);
		painelLogin.setBackground(new Color(176, 224, 230));
		painelLogin.setLayout(null);
		painelLogin.setOpaque(false);
		frmPrincipal.getContentPane().add(painelLogin);
		
		lblBemVindoMessenger = new JLabel("Bem Vindo ao Messenger");
		lblBemVindoMessenger.setBounds(47, 0, 235, 19);
		lblBemVindoMessenger.setHorizontalAlignment(SwingConstants.CENTER);
		lblBemVindoMessenger.setForeground(new Color(0, 0, 128));
		lblBemVindoMessenger.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		painelLogin.add(lblBemVindoMessenger);
		
		lblSeuEmail = new JTextField();
		lblSeuEmail.setBorder(null);
		lblSeuEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSeuEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeuEmail.setEditable(false);
		lblSeuEmail.setBackground(new Color(176, 224, 230));
		lblSeuEmail.setBounds(81, 35, 151, 20);
		lblSeuEmail.setText("Seu E-mail");
		lblSeuEmail.setColumns(10);
		painelLogin.add(lblSeuEmail);
		
		inputEmail = new JTextField("admin@messenger.com");
		inputEmail.setHorizontalAlignment(SwingConstants.CENTER);
		inputEmail.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		inputEmail.setBounds(34, 55, 247, 29);
		inputEmail.setColumns(10);
		painelLogin.add(inputEmail);
		
		lblSuaSenha = new JTextField();
		lblSuaSenha.setText("Sua Senha");
		lblSuaSenha.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuaSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSuaSenha.setEditable(false);
		lblSuaSenha.setColumns(10);
		lblSuaSenha.setBorder(null);
		lblSuaSenha.setBackground(new Color(176, 224, 230));
		lblSuaSenha.setBounds(81, 94, 151, 20);
		painelLogin.add(lblSuaSenha);
		
		inputSenha = new JPasswordField("007");
		inputSenha.setHorizontalAlignment(SwingConstants.CENTER);
		inputSenha.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		inputSenha.setBounds(34, 116, 247, 29);
		painelLogin.add(inputSenha);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{
						String email = inputEmail.getText();
						String senha = new String(inputSenha.getPassword());

						Fachada.login(email, senha);
						//JOptionPane.showMessageDialog(null, "Pessoa logada no momento: " + Fachada.getLogada().getNome()+"\n");
						
						nCxSaida = Fachada.getLogada().getCaixaSaida().size();
						nCxEntrada =  Fachada.getLogada().getCaixaEntrada().size();
						nomeLogada = Fachada.getLogada().getNome();
						emailLogada = Fachada.getLogada().getEmail();
						
						lblNomeLogada.setText("Ol\u00E1, " + nomeLogada + " (" + emailLogada + ")" );
						lblCxEntrada.setText("Sua Caixa de Entrada contem "+ nCxEntrada +" mensagem(ns)!");
						lblCxSaida.setText("Sua Caixa de Saída contem "+ nCxSaida +" mensagem(ns)!");
						painelLogin.setVisible(false);
						painelBoasVindas.setVisible(true);
						logada = Fachada.getLogada();
						if(logada instanceof Administrador) {
							menuBar.setVisible(true);
						}else {
							menuBar.setVisible(false);
						}
						
						
					}
					catch(Exception erro){
						lblMensagensLogin.setText(erro.getMessage());
						//JOptionPane.showMessageDialog(null, erro.getMessage());
					}
				}
		});
		btnEntrar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnEntrar.setForeground(new Color(255, 255, 255));
		btnEntrar.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		btnEntrar.setBackground(new Color(0, 0, 128));
		btnEntrar.setBounds(34, 159, 247, 34);
		painelLogin.add(btnEntrar);
		
		lblMensagensLogin = new JLabel("");
		lblMensagensLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagensLogin.setForeground(new Color(255, 0, 0));
		lblMensagensLogin.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblMensagensLogin.setBounds(10, 204, 306, 25);
		painelLogin.add(lblMensagensLogin);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 0, 128));
		menuBar.setBounds(0, 0, 403, 30);
		menuBar.setVisible(false);
		frmPrincipal.getContentPane().add(menuBar);
		
		mnConsultas = new JMenu("Consultas");
		mnConsultas.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		mnConsultas.setForeground(new Color(255, 255, 255));
		menuBar.add(mnConsultas);
		
		mntmMensagens = new JMenuItem("Mensagens");
		mntmMensagens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					try {
						telaconsultamensagens = new TelaConsultaMensagens();
						telaconsultamensagens.setVisible(true);
					} catch (Exception e1) {				
						e1.printStackTrace();
						
					}
				}
		});
		mnConsultas.add(mntmMensagens);
		
		mntmCursos = new JMenuItem("Cursos");
		mntmCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					telaconsultaAluno = new TelaConsultaAlunoDepart("Curso");
					telaconsultaAluno.setVisible(true); 
				} catch (Exception e1) {				
					e1.printStackTrace();
					
				}
			}
		});
		mnConsultas.add(mntmCursos);
		
		mntmDepartamentos = new JMenuItem("Departamentos");
		mntmDepartamentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					telaconsultaDepart = new TelaConsultaAlunoDepart("Departamento");
					telaconsultaDepart.setVisible(true); 
				} catch (Exception e1) {				
					e1.printStackTrace();
					
				}
			}
		});
		
		mnConsultas.add(mntmDepartamentos);
		
		mnListas = new JMenu("Listas");
		mnListas.setForeground(new Color(255, 255, 255));
		mnListas.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		menuBar.add(mnListas);
		
		mntmLMensagens = new JMenuItem("Mensagens");
		mntmLMensagens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					telalistatodasmensagens = new TelaListaTodasMensagens();
					telalistatodasmensagens.setVisible(true); 
				} catch (Exception e1) {				
					e1.printStackTrace();
					
				}
			}
		});
		mnListas.add(mntmLMensagens);
		
		mntmLPessoas = new JMenuItem("Pessoas");
		mntmLPessoas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					telalistatodaspessoas = new TelaListaTodasPessoas();
					telalistatodaspessoas.setVisible(true);
				} catch (Exception e1) {				
					e1.printStackTrace();
					
				}
			}
		});
		mnListas.add(mntmLPessoas);
		
		mnCadastro = new JMenu("Cadastro");
		mnCadastro.setForeground(new Color(255, 255, 255));
		mnCadastro.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		menuBar.add(mnCadastro);
		
		mntmAluno = new JMenuItem("Aluno");
		mntmAluno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					telacadastroaluno = new TelaCadastro("Aluno");
					telacadastroaluno.setVisible(true);
				} catch (Exception e1) {				
					e1.printStackTrace();
					
				}
			}
		});
		mnCadastro.add(mntmAluno);
		
		mntmFuncionario = new JMenuItem("Funcion\u00E1rio");
		mntmFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					telacadastrofuncionario = new TelaCadastro("Funcion\u00E1rio");
					telacadastrofuncionario.setVisible(true);
				} catch (Exception e1) {				
					e1.printStackTrace();
					
				}
			}
		});
		
		mnCadastro.add(mntmFuncionario);
		
		mnEstatsticas = new JMenu("Estat\u00EDsticas");
		mnEstatsticas.setForeground(new Color(255, 255, 255));
		mnEstatsticas.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		menuBar.add(mnEstatsticas);
		
		mntmVisualizarEstatisticas = new JMenuItem("Visualizar Estatisticas");
		mntmVisualizarEstatisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					telaestatisticas = new TelaListaEstatisticas();
					telaestatisticas.setVisible(true);
				} catch (Exception e1) {				
					e1.printStackTrace();
					
				}
			}
		});
		mnEstatsticas.add(mntmVisualizarEstatisticas);

	}
}
