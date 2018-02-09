package aplicacao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import fachada.Fachada;
import modelo.Mensagem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class TelaCaixaEntrada extends JFrame{
	private static final long serialVersionUID = 1L;
	private String id;
	private JButton btnExcluir;
	private JButton btnVisualizar;
	private JButton btnFechar;
	private JLabel lblCxEntrada;
	private JLabel lblMensagens;
	private static JLabel nlblCxEntrada;
	private static JLabel nlblCxSaida;
	private JScrollPane scrollPane;
	private DefaultListModel<String> model;
	private JList<String> JlistCxEntrada;
	private TelaViewMensagem viewMsg;

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCaixaEntrada frame = new TelaCaixaEntrada(nlblCxEntrada, nlblCxSaida);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCaixaEntrada(JLabel nlblCxEntrada, JLabel nlblCxSaida) throws Exception {
		
			
			getContentPane().setBackground(new Color(123, 104, 238));
			getContentPane().setLayout(null);
			setTitle("Caixa de Entrada de " + Fachada.getLogada().getNome());
			setBounds(100, 100, 400, 328);
	
			
			lblCxEntrada = new JLabel("Caixa de Entrada de " + Fachada.getLogada().getNome());
			lblCxEntrada.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
			lblCxEntrada.setForeground(new Color(255, 255, 255));
			lblCxEntrada.setBounds(10, 11, 357, 25);
			getContentPane().add(lblCxEntrada);
			
			lblMensagens = new JLabel("Clique na mensagem para visualiz\u00E1-la ou exclu\u00ED-la");
			lblMensagens.setForeground(new Color(255, 255, 0));
			lblMensagens.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 13));
			lblMensagens.setBounds(10, 203, 357, 14);
			getContentPane().add(lblMensagens);
			
			btnVisualizar = new JButton("Visualizar");
			btnVisualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						id = lblMensagens.getText().substring(13,15);
						viewMsg = new TelaViewMensagem(Integer.parseInt(id), "Entrada");
						viewMsg.setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						
					}
				}
			});
			btnVisualizar.setBackground(new Color(100, 149, 237));
			btnVisualizar.setForeground(Color.WHITE);
			btnVisualizar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
			btnVisualizar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
			btnVisualizar.setBounds(117, 239, 89, 40);
			btnVisualizar.setVisible(false);
			getContentPane().add(btnVisualizar);
			
			
			scrollPane = new JScrollPane();
			scrollPane.setForeground(new Color(0, 0, 128));
			scrollPane.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
			scrollPane.setBounds(10, 57, 357, 123);
			getContentPane().add(scrollPane);
			
			model = new DefaultListModel<String>();
			JlistCxEntrada = new JList<String>(model);
			JlistCxEntrada.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
			JlistCxEntrada.setForeground(new Color(0, 0, 128));
			JlistCxEntrada.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
						lblMensagens.setText("Mensagem nº: " + model.get(JlistCxEntrada.getSelectedIndex()).substring(13,15) + " selecionada");
						btnVisualizar.setVisible(true);
						btnExcluir.setVisible(true);
					}
				});
			
			for(Mensagem m :  Fachada.listarCxEntrada()) {
				model.addElement(m.cxEntrada());
			}
			
			if(Fachada.listarCxEntrada().size() > 0)
				scrollPane.setViewportView(JlistCxEntrada);
			else {
				scrollPane.setVisible(false);
				lblCxEntrada.setText("Sem mensagens na sua Caixa de Entrada!");
				lblMensagens.setText("");
			}
			btnFechar = new JButton("Fechar");
			btnFechar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					if(viewMsg !=null)
						viewMsg.dispose();
				}
			});
			btnFechar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
			btnFechar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
			btnFechar.setBackground(Color.YELLOW);
			btnFechar.setBounds(10, 239, 89, 40);
			getContentPane().add(btnFechar);
			
			
			btnExcluir = new JButton("Excluir");
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Object[] options = { "Sim", "Não" };
					int i = JOptionPane.showOptionDialog(null,"Tem certeza que deseja excluir esta mensagem?", "Excluir Mensagem",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if (i == JOptionPane.YES_OPTION) {
							
							try {
								id = lblMensagens.getText().substring(13,15);
								Fachada.apagarMensagem(Integer.parseInt(id), Fachada.getLogada());
								model.remove(JlistCxEntrada.getSelectedIndex());
								lblMensagens.setText("");
								if(Fachada.listarCxEntrada().size() == 0) {
									lblCxEntrada.setText("Sem mensagens na sua Caixa de Entrada!");
									btnVisualizar.setVisible(false);
									btnExcluir.setVisible(false);
									scrollPane.setVisible(false);
								}
								nlblCxEntrada.setText("Sua Caixa de Entrada contem "+ Fachada.listarCxEntrada().size() +" mensagens!");
								nlblCxSaida.setText("Sua Caixa de Saída contem "+ Fachada.listarCxSaida().size() +" mensagens!");
							
			
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Selecione uma mensagem!");	
							}
							
					}	
				}
			});
			btnExcluir.setBackground(new Color(165, 42, 42));
			btnExcluir.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
			btnExcluir.setForeground(new Color(255, 255, 255));
			btnExcluir.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
			btnExcluir.setBounds(225, 239, 89, 40);
			btnExcluir.setVisible(false);
			getContentPane().add(btnExcluir);
			
		
	}
	
	public TelaViewMensagem getTelaViewMensagem() {
		return viewMsg;
	}
}
