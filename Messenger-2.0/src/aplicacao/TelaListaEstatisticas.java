package aplicacao;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import fachada.Fachada;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class TelaListaEstatisticas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblNEnvMsg;
	private JLabel lblMsgEmDes;
	private JLabel lblEstatisticas;
	private JButton btnFechar;
	private JLabel lblTotalDeAlunos;
	private JTextArea textAreaMsgEmDes;
	private JTextArea textAreaNEnvMsg;
	private JTextArea textAreaTotalDeAlunos;
	private String pessoasSemEnvio, msgEmDes,totalAlunosCurso;
	private JScrollPane scrollPaneNEnvMsg;
	private JScrollPane scrollPaneMsgEmDes;
	private JScrollPane scrollPaneTotalDeAlunos;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaListaEstatisticas window = new TelaListaEstatisticas();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TelaListaEstatisticas() {
		getContentPane().setBackground(new Color(176, 224, 230));
		getContentPane().setLayout(null);
		
		
		lblEstatisticas = new JLabel("Estat\u00EDsticas");
		lblEstatisticas.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstatisticas.setForeground(new Color(0, 0, 128));
		lblEstatisticas.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblEstatisticas.setBounds(10, 11, 364, 14);
		getContentPane().add(lblEstatisticas);
		
		lblNEnvMsg = new JLabel("Pessoas que n\u00E3o enviaram Mensagens");
		lblNEnvMsg.setOpaque(true);
		lblNEnvMsg.setForeground(new Color(255, 255, 255));
		lblNEnvMsg.setHorizontalAlignment(SwingConstants.CENTER);
		lblNEnvMsg.setBackground(new Color(0, 0, 128));
		lblNEnvMsg.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		lblNEnvMsg.setBounds(0, 36, 390, 28);
		getContentPane().add(lblNEnvMsg);
		
		lblMsgEmDes = new JLabel("Mensagens com emitente igual ao destinat\u00E1rio");
		lblMsgEmDes.setOpaque(true);
		lblMsgEmDes.setHorizontalAlignment(SwingConstants.CENTER);
		lblMsgEmDes.setBackground(new Color(0, 0, 128));
		lblMsgEmDes.setForeground(new Color(255, 255, 255));
		lblMsgEmDes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		lblMsgEmDes.setBounds(0, 200, 390, 28);
		getContentPane().add(lblMsgEmDes);
		
		
		btnFechar = new JButton("Fechar");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFechar.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		btnFechar.setBorder(new LineBorder(new Color(255, 255, 255), 2, true));
		btnFechar.setBackground(Color.YELLOW);
		btnFechar.setBounds(10, 505, 89, 40);
		getContentPane().add(btnFechar);
		
		lblTotalDeAlunos = new JLabel("Total de alunos por curso");
		lblTotalDeAlunos.setOpaque(true);
		lblTotalDeAlunos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalDeAlunos.setBackground(new Color(0, 0, 128));
		lblTotalDeAlunos.setForeground(new Color(255, 255, 255));
		lblTotalDeAlunos.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		lblTotalDeAlunos.setBounds(0, 351, 390, 28);
		getContentPane().add(lblTotalDeAlunos);
		
		
		
		try {
			pessoasSemEnvio = Fachada.listaPessoasSemEnviarMsg();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		scrollPaneNEnvMsg = new JScrollPane();
		scrollPaneNEnvMsg.setBorder(null);
		scrollPaneNEnvMsg.setBounds(10, 75, 370, 102);
		getContentPane().add(scrollPaneNEnvMsg);
		
		textAreaNEnvMsg = new JTextArea();
		textAreaNEnvMsg.setEditable(false);
		textAreaNEnvMsg.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		textAreaNEnvMsg.setBackground(new Color(176, 224, 230));
		textAreaNEnvMsg.setText(pessoasSemEnvio);
		scrollPaneNEnvMsg.setViewportView(textAreaNEnvMsg);
		
		
		try {
			msgEmDes = Fachada.listaMsgsMesmoEmitRem();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		scrollPaneMsgEmDes = new JScrollPane();
		scrollPaneMsgEmDes.setBorder(null);
		scrollPaneMsgEmDes.setBounds(10, 238, 370, 102);
		getContentPane().add(scrollPaneMsgEmDes);
		
		textAreaMsgEmDes = new JTextArea();
		textAreaMsgEmDes.setEditable(false);
		textAreaMsgEmDes.setText(msgEmDes);
		textAreaMsgEmDes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		textAreaMsgEmDes.setBackground(new Color(176, 224, 230));
		scrollPaneMsgEmDes.setViewportView(textAreaMsgEmDes);
		
		try {
			
			totalAlunosCurso = Fachada.listaAlunosPorCurso();
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		scrollPaneTotalDeAlunos = new JScrollPane();
		scrollPaneTotalDeAlunos.setBorder(null);
		scrollPaneTotalDeAlunos.setBounds(10, 392, 370, 102);
		getContentPane().add(scrollPaneTotalDeAlunos);
		
		
		textAreaTotalDeAlunos = new JTextArea();
		textAreaTotalDeAlunos.setEditable(false);
		scrollPaneTotalDeAlunos.setViewportView(textAreaTotalDeAlunos);
		textAreaTotalDeAlunos.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		textAreaTotalDeAlunos.setBackground(new Color(176, 224, 230));
		textAreaTotalDeAlunos.setText(totalAlunosCurso);
		setTitle("Estat\u00EDsticas");

		setBounds(100, 100, 400, 589);
		
	}
}
