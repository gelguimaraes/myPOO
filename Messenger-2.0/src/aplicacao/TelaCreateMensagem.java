package aplicacao;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import fachada.Fachada;
import modelo.Mensagem;
import modelo.Pessoa;


public class TelaCreateMensagem extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField inputDestinatario;
	private JLabel lblEscMsg;
	private JLabel lblRemetente;
	private JLabel lblDestinatario;
	private JTextArea textAreaMsg;
	private JButton btnEnviarMensagem;
	private JScrollPane scrollPane;
	private JLabel lblMensagens;
	private JButton btnFechar;
	private JButton btnProcurar;
	private static JLabel nlblCxEntrada;
	private static JLabel nlblCxSaida;
	private TelaConsultaPessoas viewListPessoas;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCreateMensagem window = new TelaCreateMensagem(nlblCxEntrada, nlblCxSaida);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCreateMensagem(JLabel nlblCxEntrada, JLabel nlblCxSaida) {
		getContentPane().setBackground(new Color(176, 224, 230));
		setTitle("Escrever Mensagem");
		setBounds(100, 100, 420, 393);
		getContentPane().setLayout(null);
		
		lblEscMsg = new JLabel("Escrever Mensagem");
		lblEscMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblEscMsg.setForeground(new Color(0, 0, 128));
		lblEscMsg.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblEscMsg.setBounds(10, 11, 384, 24);
		getContentPane().add(lblEscMsg);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 128), 3, true));
		scrollPane.setBounds(10, 128, 384, 140);
		getContentPane().add(scrollPane);
		
		textAreaMsg = new JTextArea();
		textAreaMsg.setLineWrap(true);
		scrollPane.setViewportView(textAreaMsg);
		textAreaMsg.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		textAreaMsg.setForeground(new Color(0, 0, 128));
		textAreaMsg.setMargin(new Insets(6, 6, 6, 6));
		
		lblRemetente = new JLabel("De: "+ Fachada.getLogada().getNome() + " (" + Fachada.getLogada().getEmail() + ")");
		lblRemetente.setForeground(new Color(0, 0, 128));
		lblRemetente.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		lblRemetente.setBounds(10, 46, 384, 24);
		getContentPane().add(lblRemetente);
		
		lblDestinatario = new JLabel("Para: ");
		lblDestinatario.setForeground(new Color(0, 0, 128));
		lblDestinatario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		lblDestinatario.setBounds(10, 76, 47, 28);
		getContentPane().add(lblDestinatario);
		
		btnEnviarMensagem = new JButton("Enviar Mensagem");
		btnEnviarMensagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String texto = textAreaMsg.getText();
					Pessoa destinatario = Fachada.getEmailDestinatario(inputDestinatario.getText());
					Mensagem m = Fachada.enviarMensagem(destinatario, texto);
					textAreaMsg.setText("");
					inputDestinatario.setText("");
					lblMensagens.setText("");
					nlblCxEntrada.setText("Sua Caixa de Entrada contem "+ Fachada.listarCxEntrada().size() +" mensagens!");
					nlblCxSaida.setText("Sua Caixa de Saída contem "+ Fachada.listarCxSaida().size() +" mensagens!");
					
					JOptionPane.showMessageDialog(null, "Mensagem enviada com sucesso para:\n" 
					+ destinatario.getNome() + " (" + destinatario.getEmail() + ")\nMensagem nº: " + m.getId());
				} catch (Exception e2) {
					lblMensagens.setText(e2.getMessage()); 
				}
			}
		});
		btnEnviarMensagem.setForeground(new Color(255, 255, 255));
		btnEnviarMensagem.setBackground(new Color(0, 0, 128));
		btnEnviarMensagem.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnEnviarMensagem.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnEnviarMensagem.setBounds(10, 279, 194, 40);
		getContentPane().add(btnEnviarMensagem);
		
		inputDestinatario = new JTextField();
		inputDestinatario.setToolTipText("Digite um e-mail...");
		inputDestinatario.setMargin(new Insets(2, 10, 2, 2));
		inputDestinatario.setBackground(new Color(255, 255, 255));
		inputDestinatario.setForeground(new Color(0, 0, 128));
		inputDestinatario.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		inputDestinatario.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		inputDestinatario.setDisabledTextColor(new Color(255, 255, 255));
		inputDestinatario.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		inputDestinatario.setBounds(55, 74, 214, 30);
		inputDestinatario.setColumns(10);
		getContentPane().add(inputDestinatario);
		
		lblMensagens = new JLabel("");
		lblMensagens.setForeground(new Color(255, 0, 0));
		lblMensagens.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 11));
		lblMensagens.setBounds(10, 330, 508, 14);
		getContentPane().add(lblMensagens);
		
		btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnFechar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnFechar.setBackground(Color.YELLOW);
		btnFechar.setBounds(214, 279, 89, 40);
		getContentPane().add(btnFechar);
		
		btnProcurar = new JButton("Destinat\u00E1rios");
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				viewListPessoas = new TelaConsultaPessoas(inputDestinatario);
				viewListPessoas.setVisible(true);
			}
		});
		btnProcurar.setForeground(new Color(255, 255, 255));
		btnProcurar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnProcurar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnProcurar.setBackground(new Color(0, 0, 128));
		btnProcurar.setBounds(279, 75, 115, 30);
		getContentPane().add(btnProcurar);
		
	}
	
	public TelaConsultaPessoas getTelalistaPessoas() {
		return viewListPessoas;
	}

}
