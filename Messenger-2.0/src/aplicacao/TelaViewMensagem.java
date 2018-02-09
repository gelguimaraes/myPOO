package aplicacao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import fachada.Fachada;
import modelo.Pessoa;
import javax.swing.JScrollPane;

public class TelaViewMensagem extends JFrame {
	private static final long serialVersionUID = 1L;
	private static int id;
	private static String tipo;
	private JLabel ldlEnviada;
	private JLabel lblId;
	private JLabel lblData;
	private JTextArea textArea;
	private JButton btnFechar;
	private Pessoa enviada;
	private String preposicao;
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaViewMensagem window = new TelaViewMensagem(id, tipo);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
	}

	public TelaViewMensagem(int id, String tipo) throws Exception {
	
			setBounds(100, 100, 420, 325);
			getContentPane().setLayout(null);
			
			
				if(tipo.equals("Entrada")) {
				enviada =  Fachada.obterMensagem(id).getEmitente();
				preposicao = " por ";
				getContentPane().setBackground(new Color(123, 104, 238));
				}
				else if(tipo.equals("Saida")) {
					enviada =  Fachada.obterMensagem(id).getDestinatario();
					preposicao = " para ";
					getContentPane().setBackground(new Color(219, 112, 147));
				}
				else  {
					enviada =  Fachada.obterMensagem(id).getDestinatario();
					preposicao = " para ";
					getContentPane().setBackground(new Color(0, 139, 139));
				}
			
			setTitle("Mensagem de " +  Fachada.obterMensagem(id).getEmitente().getNome());
			
			ldlEnviada = new JLabel("Enviada"+ preposicao + enviada.getNome() + " ("+ enviada.getEmail() + ")");
			ldlEnviada.setForeground(Color.WHITE);
			ldlEnviada.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
			ldlEnviada.setBounds(10, 11, 384, 25);
			getContentPane().add(ldlEnviada);
			
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 80, 384, 130);
			getContentPane().add(scrollPane);
			
			textArea = new JTextArea( Fachada.obterMensagem(id).getTexto());
			textArea.setForeground(new Color(0, 0, 128));
			textArea.setLineWrap(true);
			scrollPane.setViewportView(textArea);
			textArea.setMargin(new Insets(5, 5, 2, 2));
			textArea.setBackground(new Color(255, 255, 255));
			textArea.setEditable(false);
			textArea.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
			
			lblData = new JLabel("Em: " +  Fachada.obterMensagem(id).getData());
			lblData.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
			lblData.setForeground(new Color(255, 255, 255));
			lblData.setBounds(139, 47, 207, 14);
			getContentPane().add(lblData);
			
			lblId = new JLabel("Mensagem nº: "+  String.format("%02d", id));
			lblId.setForeground(new Color(255, 255, 255));
			lblId.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
			lblId.setBounds(10, 47, 119, 14);
			getContentPane().add(lblId);
			
			btnFechar = new JButton("Fechar");
			btnFechar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnFechar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
			btnFechar.setBackground(new Color(255, 255, 0));
			btnFechar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
			btnFechar.setBounds(10, 228, 89, 40);
			getContentPane().add(btnFechar);
		

	}
}
