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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import fachada.Fachada;


public class TelaPrincipal {
	private JFrame frmPrincipal;
	
	private JPanel painelLogin;
	
	private JTextField lblSeuEmail;
	private JTextField lblSuaSenha;
	private JTextField inputEmail;
	
	private JPasswordField inputSenha;
	
	private JLabel lblCadastro;
	private JLabel lblMensagensLogin;
	private JPanel painelBoasVindas;
	private JLabel lblNomeLogada;
	private JLabel lblCxEntrada;
	private JLabel lblCxSaida;
	private JLabel lblBemVindoMessenger;
	private JLabel lblMensagensBoasVindas;
	
	private JButton btnCxEntrada;
	private JButton btnCxSaida;
	private JButton btnEscreverMensagem;
	private JButton btnSair;
	private JButton btnEntrar;
	private JButton btnFazerCadastro;
	
	private String nomeLogada;
	private String emailLogada;
	
	private TelaCaixaEntrada cxEntrada;
	private TelaCaixaSaida cxSaida;
	private TelaCadastro telacadastro ;
	private TelaCreateMensagem createMsg;
	
	private int nCxEntrada;
	private int nCxSaida;
	

	
	


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
					Fachada.cadastrarPessoa("João Firmino","joao", "123");
					Fachada.cadastrarPessoa("Maria Alice","maria", "abc");
					Fachada.cadastrarPessoa("José Alves","jose", "456");
					Fachada.cadastrarPessoa("José Luiz","jl", "def");
					Fachada.login("joao@messenger.com", "123");
					Fachada.enviarMensagem(Fachada.emailDestinatario("maria@messenger.com"), "Mensagem de joao para Maria");
					Fachada.logoff();
					Fachada.login("maria@messenger.com", "abc");
					Fachada.enviarMensagem(Fachada.emailDestinatario("jose@messenger.com"), "Mensagem de maria para jose");
					Fachada.logoff();
					Fachada.login("jose@messenger.com", "456");
					Fachada.enviarMensagem(Fachada.emailDestinatario("joao@messenger.com"), "Mensagem de jose para joao");
					Fachada.logoff();
					Fachada.login("joao@messenger.com", "123");
					Fachada.enviarMensagem(Fachada.emailDestinatario("jose@messenger.com"), "Mensagem de joao para jose");
					Fachada.logoff();
					Fachada.login("maria@messenger.com", "abc");
					Fachada.enviarMensagem(Fachada.emailDestinatario("joao@messenger.com"), "Mensagem de maria para joao");
					Fachada.logoff();
					Fachada.login("jose@messenger.com", "456");
					Fachada.enviarMensagem(Fachada.emailDestinatario("maria@messenger.com"), "Mensagem de jose para maria");
					Fachada.logoff();
					Fachada.login("joao@messenger.com", "123");
					Fachada.enviarMensagem(Fachada.emailDestinatario("joao@messenger.com"), "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent ultricies, nulla vel mattis finibus, tellus neque tristique velit, sed consectetur diam justo nec sapien. Sed interdum metus tellus, ac malesuada ipsum pretium eu. Aenean sollicitudin turpis nec velit interdum, id accumsan lorem commodo.");
					Fachada.logoff();	
										
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}
			
		});
		frmPrincipal.setTitle("Messenger");
		frmPrincipal.setBounds(100, 100, 400, 404);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		painelBoasVindas = new JPanel();
		painelBoasVindas.setBackground(new Color(176, 224, 230));
		painelBoasVindas.setBounds(30, 11, 316, 323);
		painelBoasVindas.setLayout(null);
		painelBoasVindas.setVisible(false);
		frmPrincipal.getContentPane().add(painelBoasVindas);
		
		lblNomeLogada = new JLabel("");
		lblNomeLogada.setForeground(new Color(0, 0, 128));
		lblNomeLogada.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 13));
		lblNomeLogada.setBounds(0, 11, 316, 25);
		painelBoasVindas.add(lblNomeLogada);
		
	
		lblCxEntrada = new JLabel("");
		lblCxEntrada.setBounds(0, 48, 316, 14);
		painelBoasVindas.add(lblCxEntrada);
		
		lblCxSaida = new JLabel("");
		lblCxSaida.setBounds(0, 118, 316, 14);
		painelBoasVindas.add(lblCxSaida);
		
		btnCxEntrada = new JButton("Visualizar Caixa de Entrada");
		btnCxEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					cxEntrada = new TelaCaixaEntrada(lblCxEntrada, lblCxSaida);
					cxEntrada.setVisible(true);
				} catch (Exception e1) {				
					e1.printStackTrace();
					
				}
			}
				
		});
		btnCxEntrada.setForeground(new Color(255, 255, 255));
		btnCxEntrada.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnCxEntrada.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnCxEntrada.setBackground(new Color(123, 104, 238));
		btnCxEntrada.setBounds(0, 73, 316, 34);
		painelBoasVindas.add(btnCxEntrada);
		
		btnCxSaida = new JButton("Visualizar Caixa de Sa\u00EDda");
		btnCxSaida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					cxSaida = new TelaCaixaSaida(lblCxEntrada, lblCxSaida);
					cxSaida.setVisible(true);
				} catch (Exception e1) {				
					e1.printStackTrace();
					
				}
			}
		});
		btnCxSaida.setForeground(new Color(255, 255, 255));
		btnCxSaida.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		btnCxSaida.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnCxSaida.setBackground(new Color(219, 112, 147));
		btnCxSaida.setBounds(0, 143, 316, 34);
		painelBoasVindas.add(btnCxSaida);
		
		btnEscreverMensagem = new JButton("Escrever Nova Mensagem");
		btnEscreverMensagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createMsg = new TelaCreateMensagem(lblCxEntrada, lblCxSaida);
				createMsg.setVisible(true);
				
			}
		});
		btnEscreverMensagem.setForeground(new Color(255, 255, 255));
		btnEscreverMensagem.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		btnEscreverMensagem.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnEscreverMensagem.setBackground(new Color(0, 0, 128));
		btnEscreverMensagem.setBounds(0, 213, 316, 51);
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
					
					//fechando todas  as telas
					if(cxEntrada != null) {
						cxEntrada.dispose();
						if(cxEntrada.getTelaViewMensagem() != null) 
							cxEntrada.getTelaViewMensagem().dispose();
					
					}
					if(cxSaida != null) {
						cxSaida.dispose();
						if(cxSaida.getTelaViewMensagem() != null) 
							cxSaida.getTelaViewMensagem().dispose();
					}
					
					if(createMsg != null) {
						createMsg.dispose();
						if(createMsg.getTelalistaPessoas() != null) 
							createMsg.getTelalistaPessoas().dispose();
					}
					
					
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
		btnSair.setBounds(265, 289, 51, 23);
		painelBoasVindas.add(btnSair);
		
		lblMensagensBoasVindas = new JLabel("");
		lblMensagensBoasVindas.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 11));
		lblMensagensBoasVindas.setForeground(new Color(255, 0, 0));
		lblMensagensBoasVindas.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagensBoasVindas.setBounds(0, 286, 316, 14);
		painelBoasVindas.add(lblMensagensBoasVindas);
		
		painelLogin = new JPanel();
		painelLogin.setBackground(new Color(176, 224, 230));
		painelLogin.setBounds(30, 11, 316, 323);
		painelLogin.setLayout(null);
		painelLogin.setOpaque(false); 
		frmPrincipal.getContentPane().add(painelLogin);
		
		lblBemVindoMessenger = new JLabel("Bem Vindo ao Messenger");
		lblBemVindoMessenger.setBounds(47, 0, 235, 19);
		painelLogin.add(lblBemVindoMessenger);
		lblBemVindoMessenger.setHorizontalAlignment(SwingConstants.CENTER);
		lblBemVindoMessenger.setForeground(new Color(0, 0, 128));
		lblBemVindoMessenger.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		
		lblSeuEmail = new JTextField();
		lblSeuEmail.setBorder(null);
		lblSeuEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSeuEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeuEmail.setEditable(false);
		lblSeuEmail.setBackground(new Color(176, 224, 230));
		lblSeuEmail.setBounds(81, 35, 151, 20);
		painelLogin.add(lblSeuEmail);
		lblSeuEmail.setText("Seu E-mail");
		lblSeuEmail.setColumns(10);
		
		inputEmail = new JTextField("joao@messenger.com");
		inputEmail.setHorizontalAlignment(SwingConstants.CENTER);
		inputEmail.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		inputEmail.setBounds(34, 55, 247, 29);
		painelLogin.add(inputEmail);
		inputEmail.setColumns(10);
		
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
		
		inputSenha = new JPasswordField("123");
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
		
		lblCadastro = new JLabel("Ainda n\u00E3o se cadastrou?");
		lblCadastro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastro.setBounds(81, 232, 151, 14);
		painelLogin.add(lblCadastro);
		
		btnFazerCadastro = new JButton("Fa\u00E7a aqui o seu Cadastro");
		btnFazerCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				telacadastro = new TelaCadastro();
				telacadastro.setVisible(true);
			}
		});
		btnFazerCadastro.setActionCommand("");
		btnFazerCadastro.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnFazerCadastro.setForeground(new Color(255, 255, 255));
		btnFazerCadastro.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		btnFazerCadastro.setBackground(new Color(0, 0, 128));
		btnFazerCadastro.setBounds(34, 257, 247, 34);
		painelLogin.add(btnFazerCadastro);
		
		lblMensagensLogin = new JLabel("");
		lblMensagensLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMensagensLogin.setForeground(new Color(255, 0, 0));
		lblMensagensLogin.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lblMensagensLogin.setBounds(0, 298, 306, 25);
		painelLogin.add(lblMensagensLogin);

	}

}
