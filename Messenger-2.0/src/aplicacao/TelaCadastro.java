package aplicacao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import fachada.Fachada;




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
	private JComboBox<String> comboBoxCurso;
	private JLabel lblCurso;
	private static String tipo;
	private static String lblcursodepartamento;
	private JComboBox<String> combocursodepartamento;
	private JComboBox<String> comboBoxDepartamento;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCadastro frame = new TelaCadastro(tipo);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaCadastro(String tipo) {
		getContentPane().setBackground(new Color(176, 224, 230));
		getContentPane().setLayout(null);
		setTitle("Cadastro de " + tipo);
		setBounds(100, 100, 400, 454);
		
		lblCadastroDeUsurio = new JLabel("Cadastro de " + tipo);
		lblCadastroDeUsurio.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeUsurio.setForeground(new Color(0, 0, 128));
		lblCadastroDeUsurio.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 15));
		lblCadastroDeUsurio.setBounds(89, 24, 235, 19);
		getContentPane().add(lblCadastroDeUsurio);
		
		lblSeuNome = new JTextField();
		lblSeuNome.setText("Nome");
		lblSeuNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeuNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSeuNome.setEditable(false);
		lblSeuNome.setColumns(10);
		lblSeuNome.setBorder(null);
		lblSeuNome.setBackground(new Color(176, 224, 230));
		lblSeuNome.setBounds(79, 127, 151, 20);
		getContentPane().add(lblSeuNome);
		
		inputNome = new JTextField();
		inputNome.setColumns(10);
		inputNome.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		inputNome.setBounds(79, 147, 245, 29);
		getContentPane().add(inputNome);
		
		lblSeuEmail = new JTextField();
		lblSeuEmail.setText(" E-mail");
		lblSeuEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblSeuEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSeuEmail.setEditable(false);
		lblSeuEmail.setColumns(10);
		lblSeuEmail.setBorder(null);
		lblSeuEmail.setBackground(new Color(176, 224, 230));
		lblSeuEmail.setBounds(79, 187, 151, 20);
		getContentPane().add(lblSeuEmail);
		
		inputEmail = new JTextField();
		inputEmail.setMargin(new Insets(2, 5, 2, 2));
		inputEmail.setColumns(10);
		inputEmail.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		inputEmail.setBounds(79, 207, 144, 29);
		getContentPane().add(inputEmail);
		
		lblmessengercombr = new JLabel("@messenger.com");
		lblmessengercombr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblmessengercombr.setForeground(new Color(0, 0, 128));
		lblmessengercombr.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		lblmessengercombr.setBounds(215, 209, 109, 25);
		getContentPane().add(lblmessengercombr);
		
		lnlSuaSenha = new JTextField();
		lnlSuaSenha.setText("Senha");
		lnlSuaSenha.setHorizontalAlignment(SwingConstants.LEFT);
		lnlSuaSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lnlSuaSenha.setEditable(false);
		lnlSuaSenha.setColumns(10);
		lnlSuaSenha.setBorder(null);
		lnlSuaSenha.setBackground(new Color(176, 224, 230));
		lnlSuaSenha.setBounds(79, 246, 151, 20);
		getContentPane().add(lnlSuaSenha);
		
		inputPass = new JPasswordField();
		inputPass.setMargin(new Insets(2, 5, 2, 2));
		inputPass.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		inputPass.setBounds(79, 268, 245, 29);
		getContentPane().add(inputPass);
		
		
		lblmensagem = new JLabel("* Todos os campos s\u00E3o obrigat\u00F3rios");
		lblmensagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblmensagem.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		lblmensagem.setForeground(new Color(255, 0, 0));
		lblmensagem.setBounds(10, 369, 364, 14);
		getContentPane().add(lblmensagem);
		
		
		String[] departamentos = {"Selecione", "DTI", "DCET", "DHU","DCJ","DACD"} ;
		String[] cursos = {"Selecione", "Sistemas para Internet", "Redes", "Direito", "Medicina", "Engenharia", "Marketing" };


		comboBoxCurso = new JComboBox<>(cursos);
		comboBoxCurso.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		comboBoxCurso.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		comboBoxCurso.setForeground(new Color(0, 0, 128));
		comboBoxCurso.setBackground(new Color(255, 255, 255));
		comboBoxCurso.setBounds(79, 87, 243, 29);
		comboBoxCurso.setVisible(false);
		getContentPane().add(comboBoxCurso);
		
		
		
		comboBoxDepartamento = new JComboBox<>(departamentos);
		comboBoxDepartamento.setForeground(new Color(0, 0, 128));
		comboBoxDepartamento.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		comboBoxDepartamento.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		comboBoxDepartamento.setBackground(new Color(255, 255, 255));
		comboBoxDepartamento.setBounds(79, 87, 243, 29);
		comboBoxDepartamento.setVisible(false);
		getContentPane().add(comboBoxDepartamento);
		
		if (tipo == "Aluno") {
			comboBoxCurso.setVisible(true);
			lblcursodepartamento = "Curso";
			combocursodepartamento = comboBoxCurso;
			
		}
		else if (tipo == "Funcion\u00E1rio") {
			comboBoxDepartamento.setVisible(true);
			lblcursodepartamento = "Departamento";
			combocursodepartamento = comboBoxDepartamento;

		}
		
		lblCurso = new JLabel(lblcursodepartamento);
		lblCurso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCurso.setBounds(79, 64, 228, 20);
		getContentPane().add(lblCurso);
		
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String email = inputEmail.getText();
					String senha = new String(inputPass.getPassword());
					String nome = inputNome.getText();
					Fachada.cadastrarPessoa(nome, email, senha, tipo, combocursodepartamento.getSelectedItem().toString());
					inputNome.setText("");
					inputEmail.setText("");
					inputPass.setText("");
					comboBoxDepartamento.setSelectedIndex(0);
					comboBoxCurso.setSelectedIndex(0);
					JOptionPane.showMessageDialog(null, "Cadastrado realizado com sucesso!");
				} catch (Exception e2) {
					lblmensagem.setText(e2.getMessage()); 
				}
			}
		});
		btnCadastrar.setForeground(Color.WHITE);
		btnCadastrar.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 12));
		btnCadastrar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnCadastrar.setBackground(new Color(0, 0, 128));
		btnCadastrar.setBounds(79, 311, 245, 34);
		getContentPane().add(btnCadastrar);

	}
}
