package aplicacao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import fachada.Fachada;
import java.awt.Insets;


public class TelaCadastro extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField lblSeuEmail;
	private JTextField inputEmail;
	private JTextField lnlSuaSenha;
	private JPasswordField inputPass;
	private JTextField lblSeuNome;
	private JTextField inputNome;
	private JLabel lblmessengercombr;
	private JButton btnCadastrar;
	private JLabel lblmensagem;
	private JLabel lblCadastroDeUsurio;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaCadastro() {
		getContentPane().setBackground(new Color(176, 224, 230));
		getContentPane().setLayout(null);
		setTitle("Cadastro de usu\u00E1rio");
		setBounds(100, 100, 400, 349);
		
		lblCadastroDeUsurio = new JLabel("Cadastro de usu\u00E1rio");
		lblCadastroDeUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeUsurio.setForeground(new Color(0, 0, 128));
		lblCadastroDeUsurio.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblCadastroDeUsurio.setBounds(79, 11, 235, 19);
		getContentPane().add(lblCadastroDeUsurio);
		
		lblSeuNome = new JTextField();
		lblSeuNome.setText("Seu Nome");
		lblSeuNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeuNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSeuNome.setEditable(false);
		lblSeuNome.setColumns(10);
		lblSeuNome.setBorder(null);
		lblSeuNome.setBackground(new Color(176, 224, 230));
		lblSeuNome.setBounds(69, 41, 151, 20);
		getContentPane().add(lblSeuNome);
		
		inputNome = new JTextField();
		inputNome.setColumns(10);
		inputNome.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		inputNome.setBounds(69, 61, 245, 29);
		getContentPane().add(inputNome);
		
		lblSeuEmail = new JTextField();
		lblSeuEmail.setText("Seu E-mail");
		lblSeuEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeuEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSeuEmail.setEditable(false);
		lblSeuEmail.setColumns(10);
		lblSeuEmail.setBorder(null);
		lblSeuEmail.setBackground(new Color(176, 224, 230));
		lblSeuEmail.setBounds(69, 101, 151, 20);
		getContentPane().add(lblSeuEmail);
		
		inputEmail = new JTextField();
		inputEmail.setMargin(new Insets(2, 5, 2, 2));
		inputEmail.setColumns(10);
		inputEmail.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		inputEmail.setBounds(69, 121, 144, 29);
		getContentPane().add(inputEmail);
		
		lblmessengercombr = new JLabel("@messenger.com");
		lblmessengercombr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblmessengercombr.setForeground(new Color(0, 0, 128));
		lblmessengercombr.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		lblmessengercombr.setBounds(205, 123, 109, 25);
		getContentPane().add(lblmessengercombr);
		
		lnlSuaSenha = new JTextField();
		lnlSuaSenha.setText("Sua Senha");
		lnlSuaSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lnlSuaSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lnlSuaSenha.setEditable(false);
		lnlSuaSenha.setColumns(10);
		lnlSuaSenha.setBorder(null);
		lnlSuaSenha.setBackground(new Color(176, 224, 230));
		lnlSuaSenha.setBounds(69, 160, 151, 20);
		getContentPane().add(lnlSuaSenha);
		
		inputPass = new JPasswordField();
		inputPass.setMargin(new Insets(2, 5, 2, 2));
		inputPass.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		inputPass.setBounds(69, 182, 245, 29);
		getContentPane().add(inputPass);
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String email = inputEmail.getText();
					String senha = new String(inputPass.getPassword());
					String nome = inputNome.getText();
					Fachada.cadastrarPessoa(nome, email, senha);
					inputNome.setText("");
					inputEmail.setText("");
					inputPass.setText("");
					JOptionPane.showMessageDialog(null, "Cadastrado realizado com sucesso!\n Você já pode fazer o login!");
				} catch (Exception e2) {
					lblmensagem.setText(e2.getMessage()); 
				}
			}
		});
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		btnCadastrar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnCadastrar.setBackground(new Color(0, 0, 128));
		btnCadastrar.setBounds(69, 225, 245, 34);
		getContentPane().add(btnCadastrar);
		
		lblmensagem = new JLabel("* Todos os campos s\u00E3o obrigat\u00F3rios");
		lblmensagem.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		lblmensagem.setForeground(new Color(255, 0, 0));
		lblmensagem.setBounds(69, 283, 245, 14);
		getContentPane().add(lblmensagem);
		

	}

}
