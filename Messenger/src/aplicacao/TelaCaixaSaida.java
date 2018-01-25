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



public class TelaCaixaSaida extends JFrame{
	private static final long serialVersionUID = 1L;
	private JList<String> JlistCxSaida;
	private JButton btnExcluir;
	private JLabel lblCxSaida;
	private JLabel lblMensagens;
	private JButton btnVisualizar;
	private String id;
	private JScrollPane scrollPane;
	private DefaultListModel<String> model;
	private JButton btnFechar;
	private TelaViewMensagem viewMsg;
	private static JLabel nlblCxSaida;
	private static JLabel nlblCxEntrada;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCaixaSaida frame = new TelaCaixaSaida(nlblCxEntrada, nlblCxSaida);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaCaixaSaida(JLabel nlblCxEntrada, JLabel nlblCxSaida) throws Exception {
			getContentPane().setBackground(new Color(219, 112, 147));
			getContentPane().setLayout(null);
			setTitle("Caixa de Saida de " + Fachada.getLogada().getNome());
			setBounds(100, 100, 400, 328);
	
			
			lblCxSaida = new JLabel("Caixa de Saida de " + Fachada.getLogada().getNome());
			lblCxSaida.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
			lblCxSaida.setForeground(new Color(255, 255, 255));
			lblCxSaida.setBounds(10, 11, 357, 25);
			getContentPane().add(lblCxSaida);
			
			lblMensagens = new JLabel("Clique na mensagem para visualiz\u00E1-la ou exclu\u00ED-la");
			lblMensagens.setForeground(new Color(255, 255, 0));
			lblMensagens.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 13));
			lblMensagens.setBounds(10, 203, 357, 14);
			getContentPane().add(lblMensagens);
			
			btnExcluir = new JButton("Excluir");
			btnExcluir.setBackground(new Color(165, 42, 42));
			btnExcluir.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
			btnExcluir.setForeground(new Color(255, 255, 255));
			btnExcluir.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
			btnExcluir.setBounds(225, 239, 89, 40);
			btnExcluir.setVisible(false);
			btnExcluir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					Object[] options = { "Sim", "Não" };
					int i = JOptionPane.showOptionDialog(null,"Tem certeza que deseja excluir esta mensagem?", "Excluir Mensagem",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);
					if (i == JOptionPane.YES_OPTION) {
						
							try {
								id = lblMensagens.getText().substring(13,15);
								Fachada.apagarMensagem(Integer.parseInt(id));
								model.remove(JlistCxSaida.getSelectedIndex());
								lblMensagens.setText("");
								if(Fachada.listarCxSaida().size() == 0) {
									lblCxSaida.setText("Sem mensagens na sua Caixa de Saída!");
									btnVisualizar.setVisible(false);
									btnExcluir.setVisible(false);
									scrollPane.setVisible(false);
								}
								nlblCxEntrada.setText("Sua Caixa de Entrada contem "+ Fachada.listarCxEntrada().size() +" mensagens!");
								nlblCxSaida.setText("Sua Caixa de Saída contem "+ Fachada.listarCxSaida().size() +" mensagens!");
										
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Selecione uma mensagem!" );
							}		
					}
				}
			});
			getContentPane().add(btnExcluir);
			
			btnVisualizar = new JButton("Visualizar");
			btnVisualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					id = lblMensagens.getText().substring(13,15);
					try {
						viewMsg = new TelaViewMensagem(Integer.parseInt(id), "Saida");
						viewMsg.setVisible(true);
					} catch (Exception e) {
						//System.out.println(e.getMessage());
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
			scrollPane.setBounds(10, 57, 357, 123);
			getContentPane().add(scrollPane);
			
			model = new DefaultListModel<String>();
			JlistCxSaida = new JList<String>(model);
			JlistCxSaida.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
			JlistCxSaida.setForeground(new Color(0, 0, 128));
			JlistCxSaida.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
						lblMensagens.setText("Mensagem nº: " + model.get(JlistCxSaida.getSelectedIndex()).substring(13,15) + " selecionada");
						btnVisualizar.setVisible(true);
						btnExcluir.setVisible(true);
					}
				});
			
			for(Mensagem m : Fachada.listarCxSaida()) {
				model.addElement(m.cxSaida());
			}
			
			if(Fachada.listarCxSaida().size() > 0)
				scrollPane.setViewportView(JlistCxSaida);
			else {
				scrollPane.setVisible(false);
				lblCxSaida.setText("Sem mensagens na sua Caixa de Saída!");
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

	}
	
	public TelaViewMensagem getTelaViewMensagem() {
		return viewMsg;
	}
}
