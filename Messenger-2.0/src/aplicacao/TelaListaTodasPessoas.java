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
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import fachada.Fachada;
import modelo.Pessoa;

public class TelaListaTodasPessoas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JList<String> listPessoas;
	private DefaultListModel<String> model;
	private ArrayList<Pessoa> listPessoasAchadas;
	private JScrollPane scrollPane;
	private JLabel lblListaPessoas;
	private JButton btnFechar;
	private JLabel lblMensagens;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListaTodasPessoas window = new TelaListaTodasPessoas();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaListaTodasPessoas() {
		getContentPane().setBackground(new Color(176, 224, 230));
		getContentPane().setLayout(null);
		
		lblListaPessoas = new JLabel("Lista de Pessoas");
		lblListaPessoas.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaPessoas.setForeground(new Color(0, 0, 128));
		lblListaPessoas.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblListaPessoas.setBounds(10, 11, 364, 14);
		getContentPane().add(lblListaPessoas);
		
		lblMensagens = new JLabel("");
		lblMensagens.setForeground(new Color(0, 0, 128));
		lblMensagens.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		lblMensagens.setBounds(10, 275, 364, 28);
		getContentPane().add(lblMensagens);
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 128), 2, true));
		scrollPane.setBounds(10, 36, 364, 228);
		scrollPane.setForeground(new Color(0, 0, 128));
		scrollPane.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		getContentPane().add(scrollPane);
		
		model = new DefaultListModel<String>();
		listPessoas = new JList<String>(model);
		listPessoas.setForeground(new Color(0, 0, 128));
		listPessoas.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
		
		
		try {
			listPessoasAchadas = Fachada.listarPessoas("");
			for(Pessoa p : listPessoasAchadas) {
				model.addElement(p.toString());
			}
			if(listPessoasAchadas.size() > 0) {
				lblMensagens.setText("Total de Pessoas: " + listPessoasAchadas.size());
				scrollPane.setViewportView(listPessoas);
				
			}else {
				lblMensagens.setText("Nenhuma pessoa foi encontrada!");
				scrollPane.setVisible(false);
			}
			
		} catch (Exception e) {
			lblMensagens.setText(e.getMessage());
		}
		
		btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnFechar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnFechar.setBackground(Color.YELLOW);
		btnFechar.setBounds(10, 314, 89, 40);
		getContentPane().add(btnFechar);
		setTitle("Lista de Pessoas");

		setBounds(100, 100, 400, 398);
		
	}
}
