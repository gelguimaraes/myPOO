import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Aplicacao  {

	private JFrame frame;
	private final int DIMENSAO = 4;		//TAMANHO DA MATRIZ DE BOTÕES
	private JButton[][] matriz = new JButton[DIMENSAO][DIMENSAO];
	private JLabel mensagens;
	private JButton primeirobotao, segundobotao;
	private JButton botaoselecionado;
	private JButton btnReiniciar;
	private int selecionados;
	private boolean acertou;
	private JLabel lblerros;
	private JLabel lblacertos;
	private JogoMemoria jogo;
	private boolean fimJogo = false;
	private int scoreA, scoreE;
	private JLabel placar;
	private JLabel background;
	private JLabel time;
	private Song audio = new Song();
	private JLabel lblcontador;
	private Integer contador = 60;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicacao window = new Aplicacao();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Aplicacao() {
		initialize();
		audio.player("p");
		//audio.player("m");
		contador();
	}


	public void contador() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    while (contador >= -1) {
                    	lblcontador.setText(contador.toString()+" seg");
                        Thread.sleep(1000);//espera 1 segundo
                        contador--;
                        //System.out.println(contador);
                        if(contador==-1) {
                			fimJogo = true;
                			audio.player("t");
                		}
                        if(fimJogo) {
                			for(int i=0; i < DIMENSAO; i++)
                				for(int j=0; j < DIMENSAO; j++) {
                					matriz[i][j].setIcon(new ImageIcon(Aplicacao.class.getResource("/images/0.jpg")));
                					matriz[i][j].setEnabled(true);
                				}
                			jogo = new JogoMemoria();
                			jogo.initialize();
                			selecionados=0;
                			lblacertos.setText("0");
                			lblerros.setText("0");
                			fimJogo = false;
                			mensagens.setIcon(new ImageIcon(Aplicacao.class.getResource("/images/b.png")));
                			String mens;
							if(contador>-1)
                				mens ="PARABÉNS VOCÊ VENCEU!";
							else
								mens ="VOCÊ PERDEU!";

							JOptionPane.showMessageDialog(null, mens+"\nSeu placar foi:\nAcertos: "+ scoreA +"\nErros: "+ scoreE +"\nClique em OK para jogar novamente!");
                			contador= 60;
                        } 
                    } 
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);// cria uma nova Thread
        thread.start();
        
    }
	
	
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Jogo da Memoria");
		frame.setBounds(100, 100, 452, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		jogo = new JogoMemoria();
		jogo.initialize();
		

		//inicializar a matriz de buttons
		for(int i=0; i < DIMENSAO; i++){
			for(int j=0; j < DIMENSAO; j++){
				matriz[i][j] = new JButton("");

				frame.getContentPane().add(matriz[i][j]);
				
				matriz[i][j].setBounds(i*70, j*70, 70, 70);		//40x40
				//matriz[i][j].setBackground(Color.LIGHT_GRAY);	//fundo
				matriz[i][j].setIcon(new ImageIcon(Aplicacao.class.getResource("/images/0.jpg")));
				//registrar evento de click para cada botao
				
				matriz[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {	
						botaoselecionado = (JButton)e.getSource();
						
						
						//-----------SINGLE CLICK DO MOUSE----------------------------
							if(e.getClickCount()==1 && botaoselecionado.isEnabled()==true) { 
								int x = botaoselecionado.getX()/70;
								int y = botaoselecionado.getY()/70;
								System.out.println("click na posicao:"+  x + "-" + y);
								
								if(botaoselecionado==primeirobotao) {
									return;			// aguardar a selecao do segundo botao
								}
								
								selecionados++;
								if(selecionados == 1) {	//PRIMEIRO BOTAO SELECIONADO DO PAR 
									primeirobotao = botaoselecionado;
									//primeirobotao.setBackground(jogo.getColor(x,y));
									//primeirobotao.setBackground(Color.BLUE);
									primeirobotao.setIcon(new ImageIcon(Aplicacao.class.getResource("/images/"+ jogo.getPath(x,y) +".jpg")));
								}
								else 
									if(selecionados == 2) { //SEGUNDO BOTAO SELECIONADO DO PAR
										segundobotao = botaoselecionado;
										//segundobotao.setBackground(jogo.getColor(x,y));
										//segundobotao.setBackground(Color.BLUE);
										segundobotao.setIcon(new ImageIcon(Aplicacao.class.getResource("/images/"+ jogo.getPath(x,y) +".jpg")));
										//System.out.println("x1: " + primeirobotao.getX() + " y1: " +primeirobotao.getY() + " x2: " +segundobotao.getX() + " y2: " +segundobotao.getY() );
										acertou = jogo.isSamePath(primeirobotao.getX()/70, primeirobotao.getY()/70, segundobotao.getX()/70, segundobotao.getY()/70);
														
										if(acertou){
											//fixar o par de botoes selecionados na tela
											primeirobotao.setEnabled(false);
											segundobotao.setEnabled(false);
											scoreA = jogo.setYes(1, fimJogo);
											lblacertos.setText(""+jogo.getYes());
											mensagens.setIcon(new ImageIcon(Aplicacao.class.getResource("/images/a.png")));
											selecionados=0;
											
											int acertos = jogo.getYes();
											if (acertos<=7)
												audio.player("a");
											else {
												fimJogo = true;
												audio.player("v");
												
											}
											
										}	
										else {
											scoreE = jogo.setNo(1, fimJogo);
											lblerros.setText(""+jogo.getNo());
											mensagens.setIcon(new ImageIcon(Aplicacao.class.getResource("/images/e.png")));
											audio.player("e");

										}
									}
				
							}
							else 
								//-----------DUPLO CLICK DO MOUSE-----------------------------------------
								if(e.getClickCount()==2 && botaoselecionado.isEnabled()==true) { 
									if(!acertou && (botaoselecionado == primeirobotao || botaoselecionado==segundobotao)) {
										//esconder o par de botoes
										primeirobotao.setIcon(new ImageIcon(Aplicacao.class.getResource("/images/0.jpg")));
										segundobotao.setIcon(new ImageIcon(Aplicacao.class.getResource("/images/0.jpg")));
										selecionados=0;
										mensagens.setIcon(new ImageIcon(Aplicacao.class.getResource("/images/j.png")));

									}
								}

					}
					
				});		

			}
		}


		btnReiniciar = new JButton("");
		btnReiniciar.setBorderPainted(false);
		btnReiniciar.setBackground(new Color(0,128,128,0));
		btnReiniciar.setOpaque(false);
		btnReiniciar.setIcon(new ImageIcon(Aplicacao.class.getResource("/images/rj.png")));
		btnReiniciar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//reiniciar
				for(int i=0; i < DIMENSAO; i++)
					for(int j=0; j < DIMENSAO; j++) {
						matriz[i][j].setIcon(new ImageIcon(Aplicacao.class.getResource("/images/0.jpg")));
						matriz[i][j].setEnabled(true);
					}
				
				
				jogo = new JogoMemoria();
				jogo.initialize();
				audio.player("p");
				selecionados=0;
				//jogo.acertos=0;
				//jogo.erros=0;
				lblacertos.setText("0");
				lblerros.setText("0");
				mensagens.setIcon(new ImageIcon(Aplicacao.class.getResource("/images/r.png")));
				contador = 60;
			}
		});
		
		btnReiniciar.setBounds(284, 337, 119, 73);
		frame.getContentPane().add(btnReiniciar);

		mensagens = new JLabel();
		mensagens.setHorizontalAlignment(SwingConstants.CENTER);
		mensagens.setFont(new Font("Tahoma", Font.BOLD, 16));
		mensagens.setBounds(42, 296, 365, 30);
		mensagens.setIcon(new ImageIcon(Aplicacao.class.getResource("/images/b.png")));
		frame.getContentPane().add(mensagens);
		
		lblacertos = new JLabel("0");
		lblacertos.setHorizontalAlignment(SwingConstants.CENTER);
		lblacertos.setForeground(new Color(0, 100, 0));
		lblacertos.setFont(new Font("Tahoma", Font.BOLD, 42));
		lblacertos.setBounds(312, 45, 41, 56);
		frame.getContentPane().add(lblacertos);
		
		lblerros = new JLabel("0");
		lblerros.setHorizontalAlignment(SwingConstants.CENTER);
		lblerros.setForeground(new Color(255, 0, 0));
		lblerros.setFont(new Font("Tahoma", Font.BOLD, 42));
		lblerros.setBounds(360, 45, 65, 56);
		frame.getContentPane().add(lblerros);
		
		placar = new JLabel("");
		placar.setIcon(new ImageIcon(Aplicacao.class.getResource("/images/placar_minion.png")));
		placar.setBounds(299, 0, 133, 295);
		frame.getContentPane().add(placar);
		
		time = new JLabel("");
		time.setIcon(new ImageIcon(Aplicacao.class.getResource("/images/time.png")));
		time.setBounds(42, 339, 78, 71);
		frame.getContentPane().add(time);
		
		lblcontador = new JLabel("");
		lblcontador.setHorizontalAlignment(SwingConstants.CENTER);
		lblcontador.setForeground(Color.WHITE);
		lblcontador.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblcontador.setBounds(147, 337, 119, 50);
		frame.getContentPane().add(lblcontador);
		
		background = new JLabel("");
		background.setIcon(new ImageIcon(Aplicacao.class.getResource("/images/background.png")));
		background.setBounds(0, 0, 442, 422);
		frame.getContentPane().add(background);
		


	}
}
