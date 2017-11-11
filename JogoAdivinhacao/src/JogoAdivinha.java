import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class JogoAdivinha {

	private JFrame janelaJogo;
	private JLabel tituloJogo; 
	private JTextField campoNumero;
	private JButton botaoJogar;
	private JButton botaoReiniciar;
	private JLabel textoDescricao;
	private JTextArea resultado;
	private JLabel background;
	private int cont = 0;
	private int n = (int)(Math.random()*100); //entre 0 e 1
	private JList<String> ultimosNumeros;
	private boolean fim = false;
	DefaultListModel<String> model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JogoAdivinha window = new JogoAdivinha();
					window.janelaJogo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JogoAdivinha() {
		initialize();
	}
	
	public int geraNewNum() {
		int n = (int)(Math.random()*100); //entre 0 e 1
		return n;
	}
	
	
	public String verificaSorteio(int num) {
		String texto1="", texto2="", resultado="";
		cont++;
		if(n<num )
			 texto1="O número sorteado é menor e ";
		 if(n>num)
			 texto1="O número sorteado é maior e ";
		 if (n != num) {
			 if(Math.abs(n-num)<=5  )
				 texto2="tá quente!";
			 else
				 texto2="tá frio!";
		 }
		 resultado = cont + "ª Tentativa!\n" + texto1 + texto2 + "" ;
		 if(n==num && cont <=6) {
			  resultado = "Parabéns!\nVocê acertou na " + cont + "ª tentativa";
			  fim=true;
		 }
		 if(n!=num && cont == 6) {
			 resultado = "Você esgotou o número de tentativas!\nO número sorteado foi: " + n;
			 fim=true;
		 }
		
		return resultado;
		
	}

	public int ReiniciaJogo() {
		cont=0;
		campoNumero.setText("");
		resultado.setText("");
		model.clear();
		fim=false;
		int n = geraNewNum();
		return n;
	}
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		janelaJogo = new JFrame();
		janelaJogo.getContentPane().setBackground(new Color(0, 128, 128));
		janelaJogo.getContentPane().setLayout(null);
		
		
		campoNumero = new JTextField();
		campoNumero.setBorder(new LineBorder(new Color(0, 128, 128), 3));
		campoNumero.setFont(new Font("Tekton Pro", Font.BOLD, 21));
		campoNumero.setForeground(new Color(138, 43, 226));
		campoNumero.setBackground(new Color(255, 255, 255));
		campoNumero.setHorizontalAlignment(SwingConstants.CENTER);
		campoNumero.setBounds(155, 101, 92, 48);
		campoNumero.setColumns(2);
		janelaJogo.getContentPane().add(campoNumero); //adiciona o TextField
		
		
		tituloJogo = new JLabel("Jogo de Adivinha\u00E7\u00E3o ");
		tituloJogo.setHorizontalAlignment(SwingConstants.CENTER);
		tituloJogo.setForeground(new Color(255, 255, 255));
		tituloJogo.setFont(new Font("Tekton Pro", Font.BOLD, 33));
		tituloJogo.setBounds(46, 11, 335, 53);
		janelaJogo.getContentPane().add(tituloJogo);
		
		textoDescricao = new JLabel("Adivinhe um n\u00FAmero entre 0 e 99, voc\u00EA tem 6 tentativas!");
		textoDescricao.setFont(new Font("Tekton Pro", Font.PLAIN, 15));
		textoDescricao.setForeground(new Color(255, 255, 255));
		textoDescricao.setHorizontalAlignment(SwingConstants.CENTER);
		textoDescricao.setBounds(10, 63, 406, 14);
		janelaJogo.getContentPane().add(textoDescricao);
		

		resultado = new JTextArea();
		resultado.setLineWrap(true);
		resultado.setWrapStyleWord(true);
		resultado.setForeground(new Color(255, 255, 0));
		resultado.setFont(new Font("Tekton Pro", Font.BOLD, 24));
		resultado.setBounds(29, 222, 240, 113);	
		resultado.setBackground(new Color(0,128,128,0));
		resultado.setOpaque(false);
		janelaJogo.getContentPane().add(resultado);
		
		model = new DefaultListModel<String>();
		
		ultimosNumeros = new JList<String>(model);
		ultimosNumeros.setBounds(277, 222, 133, 113);
		ultimosNumeros.setBorder(null);
		ultimosNumeros.setForeground(new Color(255, 165, 0));
		ultimosNumeros.setFont(new Font("Tekton Pro", Font.BOLD, 15));
		ultimosNumeros.setBackground(new Color(0, 128, 128));
		janelaJogo.getContentPane().add(ultimosNumeros);
		
		janelaJogo.setBackground(new Color(255, 255, 255));
		janelaJogo.setTitle("Jogo de Adivinha\u00E7\u00E3o");
		janelaJogo.setBounds(100, 100, 436, 400);
		janelaJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		botaoJogar = new JButton("Jogar");
		botaoJogar.setFont(new Font("Tekton Pro", Font.BOLD, 20));
		botaoJogar.setBackground(new Color(240, 255, 255));
		botaoJogar.setForeground(new Color(148, 0, 211));
		botaoJogar.setBounds(114, 160, 92, 38);
		botaoJogar.addActionListener(new ActionListener() {//classe anonima
			public void actionPerformed(ActionEvent arg0) {
				int num =  Integer.parseInt(campoNumero.getText());
				resultado.setText(verificaSorteio(num));
				if(cont<=6)
				model.add(0,"Último número: " + num);
				if(fim || cont==6) {
					JOptionPane.showMessageDialog(null, "O Jogou finalizou, clique OK para jogar novamente!");
					n = ReiniciaJogo();
				}
					
			}
		});
		janelaJogo.getContentPane().add(botaoJogar);  //Adiciona o botao 
		
		botaoReiniciar = new JButton("Reiniciar");
		botaoReiniciar.setBackground(new Color(240, 255, 255));
		botaoReiniciar.setForeground(Color.RED);
		botaoReiniciar.setFont(new Font("Tekton Pro", Font.BOLD, 14));
		botaoReiniciar.setBounds(216, 160, 85, 38);
		botaoReiniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				n = ReiniciaJogo();
			}
		});
		janelaJogo.getContentPane().add(botaoReiniciar);
		
		background = new JLabel("");
		background.setIcon(new ImageIcon(JogoAdivinha.class.getResource("/img/interrogacao.png")));
		background.setBounds(0, 0, 426, 367);
		janelaJogo.getContentPane().add(background);
	}
}
