package aplicacao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import fachada.Fachada;
import modelo.Mensagem;



public class TelaListaTodasMensagens extends JFrame{
	private static final long serialVersionUID = 1L;
	private String id;
	private JButton btnVisualizar;
	private JButton btnFechar;
	private JLabel lbltotalMensagens;
	private JLabel lblAllMensagens;
	private JLabel lblMensagens;
	private JScrollPane scrollPane;
	private DefaultListModel<String> model;
	private JList<String> JlistAllMsgs;
	private TelaViewMensagem telaviewMsg;
	
	

	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListaTodasMensagens frame = new TelaListaTodasMensagens();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaListaTodasMensagens() throws Exception {
		
			
			getContentPane().setBackground(new Color(0, 139, 139));
			getContentPane().setLayout(null);
			setTitle("Lista de Todas as Mensagens");
			setBounds(100, 100, 400, 480);
	
			
			lblAllMensagens = new JLabel("Lista de Todas as Mensagens");
			lblAllMensagens.setHorizontalAlignment(SwingConstants.CENTER);
			lblAllMensagens.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
			lblAllMensagens.setForeground(new Color(255, 255, 255));
			lblAllMensagens.setBounds(10, 11, 357, 25);
			getContentPane().add(lblAllMensagens);
			
			lblMensagens = new JLabel("Clique na mensagem para visualiz\u00E1-la");
			lblMensagens.setForeground(new Color(255, 255, 0));
			lblMensagens.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 13));
			lblMensagens.setBounds(10, 371, 357, 14);
			getContentPane().add(lblMensagens);
			
		
			
	
			scrollPane = new JScrollPane();
			scrollPane.setBorder(new LineBorder(new Color(0, 128, 128), 2));
			scrollPane.setForeground(new Color(0, 0, 128));
			scrollPane.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
			scrollPane.setBounds(10, 47, 370, 290);
			getContentPane().add(scrollPane);
			
			model = new DefaultListModel<String>();
			JlistAllMsgs = new JList<String>(model);
			JlistAllMsgs.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 11));
			JlistAllMsgs.setForeground(new Color(0, 0, 128));
			JlistAllMsgs.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					
					String toString = model.get(JlistAllMsgs.getSelectedIndex());
					int ind;
					ind = toString.indexOf("º");
					lblMensagens.setText("Mensagem nº: " + toString.substring(ind + 2, ind + 5) + " selecionada");
					btnVisualizar.setVisible(true);
					}
				});
			
			for(Mensagem m :  Fachada.listarAllMensagens()) {
				model.addElement(m.toString());
			}
			
			
			if(Fachada.listarAllMensagens().size() > 0)
				scrollPane.setViewportView(JlistAllMsgs);
			else {
				scrollPane.setVisible(false);
				lblAllMensagens.setText("Sem mensagens!");
				lblMensagens.setText("");
			}
			
			
			
			
			btnFechar = new JButton("Fechar");
			btnFechar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					if(telaviewMsg !=null)
						telaviewMsg.dispose();
				}
			});
			btnFechar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
			btnFechar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
			btnFechar.setBackground(Color.YELLOW);
			btnFechar.setBounds(10, 396, 89, 40);
			getContentPane().add(btnFechar);
			
			btnVisualizar = new JButton("Visualizar");
			btnVisualizar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						
						int ind;
						ind = lblMensagens.getText().indexOf("º");
						id = lblMensagens.getText().substring(ind + 4 , ind + 6);
						
						telaviewMsg = new TelaViewMensagem(Integer.parseInt(id), "");
						telaviewMsg.setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage());
						
					}
				}
			});
			btnVisualizar.setBackground(new Color(100, 149, 237));
			btnVisualizar.setForeground(Color.WHITE);
			btnVisualizar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
			btnVisualizar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
			btnVisualizar.setBounds(117, 396, 89, 40);
			btnVisualizar.setVisible(false);
			getContentPane().add(btnVisualizar);
			

			lbltotalMensagens = new JLabel("Total de mensagens: " + Fachada.listarAllMensagens().size());
			lbltotalMensagens.setForeground(new Color(255, 255, 255));
			lbltotalMensagens.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
			lbltotalMensagens.setBounds(10, 346, 229, 25);
			getContentPane().add(lbltotalMensagens);
			
		
	}
	
	public TelaViewMensagem getTelaViewMensagem() {
		return telaviewMsg;
	}
}
